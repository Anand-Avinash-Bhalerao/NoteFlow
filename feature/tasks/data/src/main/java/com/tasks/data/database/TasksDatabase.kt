package com.tasks.data.database

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tasks.data.model.TasksEntity
import com.tasks.data.util.convertors.DateConverter
import com.tasks.data.util.convertors.UUIDConverter

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [TasksEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class TasksDatabase : RoomDatabase(){
    abstract fun taskDAO() : TasksDatabaseDAO
}