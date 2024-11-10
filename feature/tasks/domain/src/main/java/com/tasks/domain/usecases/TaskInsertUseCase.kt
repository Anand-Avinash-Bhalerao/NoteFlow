package com.tasks.domain.usecases

import com.tasks.domain.model.TasksInfo
import com.tasks.domain.repository.TaskRepository
import javax.inject.Inject

class TaskInsertUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke(tasksInfo: TasksInfo){
        // we can add the functionality of saving the task on server to expand the app.
        taskRepository.addTask(tasksInfo)
    }
}