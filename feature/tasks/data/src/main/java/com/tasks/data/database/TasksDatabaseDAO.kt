package com.tasks.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.tasks.data.model.TasksEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDatabaseDAO {

    @Query("SELECT * FROM tasks_table")
    fun getTasks() : Flow<List<TasksEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TasksEntity)

    @Delete
    suspend fun delete(task : TasksEntity)

    @Query("DELETE FROM tasks_table WHERE status = 1")
    suspend fun deleteCompletedTasks()

    @Update
    suspend fun update(task : TasksEntity)
}