package com.tasks.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasks.domain.model.TasksInfo
import com.tasks.domain.usecases.TaskDeleteAllCompletedUseCase
import com.tasks.domain.usecases.TaskDeleteUseCase
import com.tasks.domain.usecases.TaskGetAllUseCase
import com.tasks.domain.usecases.TaskInsertUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class TasksViewModel @Inject constructor(
    private val getAllUseCase: TaskGetAllUseCase,
    private val insertUseCase: TaskInsertUseCase,
    private val deleteUseCase: TaskDeleteUseCase,
    private val deleteAllCompletedUseCase: TaskDeleteAllCompletedUseCase
) : ViewModel() {

    private val tasksStateFlow = MutableStateFlow<List<TasksInfo>>(emptyList())
    val taskInfoList = tasksStateFlow.asStateFlow()

    init {
        getAllTasks()
    }

    private fun getAllTasks() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllUseCase().distinctUntilChanged()
                .collect { listOfTasks ->
                    tasksStateFlow.value = listOfTasks
                }
        }
    }

    fun addTask(task: TasksInfo) = viewModelScope.launch {
        insertUseCase(task)
    }

    fun setTaskStatus(task: TasksInfo) = viewModelScope.launch {

        task.status = !task.status
        insertUseCase(task)
        tasksStateFlow.value =
            tasksStateFlow.value.sortedWith(compareBy<TasksInfo> { it.status }.thenBy { it.priority }
                .thenBy { it.title })
    }

    fun deleteTask(task: TasksInfo) = viewModelScope.launch {
        deleteUseCase(task)
    }

    fun deleteAllCompletedTasks() = viewModelScope.launch {
        deleteAllCompletedUseCase()
    }

}