package com.tasks.domain.usecases

import com.tasks.domain.model.TasksInfo
import com.tasks.domain.repository.TaskRepository
import javax.inject.Inject

class TaskDeleteUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(tasksInfo: TasksInfo) {
        // expand to database on server.
        taskRepository.deleteTask(tasksInfo)
    }
}