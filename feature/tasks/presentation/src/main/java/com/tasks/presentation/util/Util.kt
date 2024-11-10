package com.tasks.presentation.util

import androidx.compose.ui.graphics.Color
import com.tasks.domain.util.Constants.Tasks.HIGH_PRIORITY
import com.tasks.domain.util.Constants.Tasks.LOW_PRIORITY
import com.tasks.domain.util.Constants.Tasks.MEDIUM_PRIORITY

fun Int.getColorFromPriority() : Color {
    val lowPriority = Color(0xFFCCFF90)
    val mediumPriority = Color(0xFFFFD180)
    val highPriority = Color(0xFFFF8A80)
    return when (this) {
        LOW_PRIORITY -> lowPriority
        MEDIUM_PRIORITY -> mediumPriority
        HIGH_PRIORITY -> highPriority
        else -> lowPriority
    }
}