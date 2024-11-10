package com.tasks.data.repositoryImpl

import com.tasks.data.database.TasksDatabaseDAO
import com.tasks.data.util.convertToTaskEntity
import com.tasks.data.util.convertToTasksInfo
import com.tasks.domain.model.TasksInfo
import com.tasks.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDatabaseDAO: TasksDatabaseDAO) :
    TaskRepository {
    override suspend fun addTask(task: TasksInfo) =
        taskDatabaseDAO.insert(task.convertToTaskEntity())

    override suspend fun setTaskStatus(task: TasksInfo) =
        taskDatabaseDAO.update(task.convertToTaskEntity())


    override suspend fun deleteTask(task: TasksInfo) =
        taskDatabaseDAO.delete(task.convertToTaskEntity())

    override suspend fun deleteCompletedTasks() =
        taskDatabaseDAO.deleteCompletedTasks()

    override suspend fun getAllTasks(): Flow<List<TasksInfo>> {
        return taskDatabaseDAO.getTasks().map { tasksEntityList ->
            tasksEntityList.map { it.convertToTasksInfo() }
        }
    }
}