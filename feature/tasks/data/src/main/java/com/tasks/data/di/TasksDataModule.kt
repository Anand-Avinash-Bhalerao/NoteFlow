package com.tasks.data.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.tasks.data.database.TasksDatabase
import com.tasks.data.database.TasksDatabaseDAO
import com.tasks.data.repositoryImpl.TaskRepositoryImpl
import com.tasks.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TasksDataModule {

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun providesTasksDAO(tasksDatabase: TasksDatabase): TasksDatabaseDAO = tasksDatabase.taskDAO()

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideTasksDatabase(@ApplicationContext context: Context): TasksDatabase =
        Room.databaseBuilder(
            context,
            TasksDatabase::class.java,
            "tasks_df"
        ).fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun providesTaskRepositoryImpl(tasksDatabaseDAO: TasksDatabaseDAO) : TaskRepository = TaskRepositoryImpl(tasksDatabaseDAO)
}