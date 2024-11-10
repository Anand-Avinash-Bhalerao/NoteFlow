package com.tasks.domain.di

import com.tasks.domain.repository.TaskRepository
import com.tasks.domain.usecases.TaskDeleteAllCompletedUseCase
import com.tasks.domain.usecases.TaskDeleteUseCase
import com.tasks.domain.usecases.TaskGetAllUseCase
import com.tasks.domain.usecases.TaskInsertUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object TaskDomainModule {
    @Singleton
    @Provides
    fun providesTaskDeleteAllCompletedUseCase(taskRepository: TaskRepository) = TaskDeleteAllCompletedUseCase(taskRepository)

    @Singleton
    @Provides
    fun providesTaskInsertUseCase(taskRepository: TaskRepository) = TaskInsertUseCase(taskRepository)

    @Singleton
    @Provides
    fun providesTaskDeleteUseCase(taskRepository: TaskRepository) = TaskDeleteUseCase(taskRepository)

    @Singleton
    @Provides
    fun providesTaskGetAllUseCase(taskRepository: TaskRepository) = TaskGetAllUseCase(taskRepository)
}