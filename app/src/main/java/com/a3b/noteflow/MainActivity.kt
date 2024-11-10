package com.a3b.noteflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.a3b.noteflow.ui.theme.NoteFlowTheme
import com.billion_dollor_company.notesapp.ui.screen.tasks.TaskScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteFlowTheme {
                TaskScreen()
            }
        }
    }
}
