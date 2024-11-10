package com.tasks.domain.repository

import com.tasks.domain.model.TasksInfo
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun addTask(task: TasksInfo)

    suspend fun setTaskStatus(task: TasksInfo)
    suspend fun deleteTask(task: TasksInfo)

    suspend fun deleteCompletedTasks()
    suspend fun getAllTasks(): Flow<List<TasksInfo>>
}