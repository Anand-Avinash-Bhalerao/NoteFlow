package com.tasks.domain.usecases

import com.tasks.domain.model.TasksInfo
import com.tasks.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskGetAllUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(): Flow<List<TasksInfo>> {
        return taskRepository.getAllTasks()
    }
}