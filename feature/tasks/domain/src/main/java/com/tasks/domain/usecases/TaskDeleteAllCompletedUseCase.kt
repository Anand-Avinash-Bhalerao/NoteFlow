package com.tasks.domain.usecases

import com.tasks.domain.repository.TaskRepository
import javax.inject.Inject

class TaskDeleteAllCompletedUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend operator fun invoke() {
        taskRepository.deleteCompletedTasks()
    }
}