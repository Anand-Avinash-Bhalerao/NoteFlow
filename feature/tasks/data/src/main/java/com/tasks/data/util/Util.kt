package com.tasks.data.util

import android.annotation.SuppressLint
import com.tasks.data.model.TasksEntity
import com.tasks.domain.model.TasksInfo

@SuppressLint("NewApi")
fun TasksInfo.convertToTaskEntity(): TasksEntity =
    TasksEntity(this.uid, this.title, this.status, this.entryDate, this.priority)

fun TasksEntity.convertToTasksInfo(): TasksInfo =
    TasksInfo(this.uid, this.title, this.status, this.entryDate, this.priority)
