package com.tasks.domain.model

import com.tasks.domain.util.Constants
import java.time.LocalDateTime
import java.util.UUID

data class TasksInfo(
    val uid: UUID = UUID.randomUUID(),

    val title: String,

    var status: Boolean = Constants.Tasks.NOT_COMPLETED,

    val entryDate: LocalDateTime,

    var priority: Int = Constants.Tasks.LOW_PRIORITY
)