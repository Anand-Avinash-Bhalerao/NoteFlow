package com.billion_dollor_company.notesapp.ui.screen.tasks

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.a3b.commonui.components.CommonScaffold
import com.a3b.commonui.components.ConfirmationDialog
import com.a3b.commonui.components.EmptyLogo
import com.a3b.commonui.components.ListHolder
import com.a3b.presentation.R
import com.tasks.domain.model.TasksInfo
import com.tasks.presentation.TasksViewModel
import com.tasks.presentation.components.AddTaskBottomSheet
import com.tasks.presentation.components.TaskCard

@OptIn(ExperimentalFoundationApi::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskScreen(
    viewModel: TasksViewModel = hiltViewModel()
) {
    // this contains the entire list of tasks.
    val tasksList = viewModel.taskInfoList.collectAsState().value
        .sortedWith(compareBy<TasksInfo> { it.status }.thenBy { it.priority }.thenBy { it.title })

    // used for opening the delete alert dialog. if set to true then dialog will open.
    var isDeleteDialogOpen by remember {
        mutableStateOf(false)
    }

    var currentSelectedTask by remember {
        mutableStateOf<TasksInfo?>(null)
    }

    var isTopAppBarMenuExpanded by remember {
        mutableStateOf(false)
    }

    // to delete a selected note.
    if (isDeleteDialogOpen) {
        ConfirmationDialog(
            onDismissRequest = {
                isDeleteDialogOpen = false
            },
            onConfirmation = {
                isDeleteDialogOpen = false
                if (currentSelectedTask != null) {
                    viewModel.deleteTask(currentSelectedTask!!)
                }
            },
            dialogTitle = stringResource(R.string.delete_dialog_title),
            dialogText = stringResource(R.string.delete_dialog_text),
            icon = Icons.Default.Delete
        )
    }

    // variables for opening add tasks bottom sheet.
    // if set to true bottom sheet opens
    var isBottomSheetOpen by remember {
        mutableStateOf(false)
    }
    if (isBottomSheetOpen) {
        AddTaskBottomSheet(
            onDismiss = {
                isBottomSheetOpen = false
            },
            onAccept = { task ->
                viewModel.addTask(task)
                isBottomSheetOpen = false
            }
        )
    }

    CommonScaffold(
        title = stringResource(R.string.page_title),
        onFloatingButtonClick = {
            isBottomSheetOpen = true
        },
        floatingButtonIcon = Icons.Default.AddTask,
        actionButton = {
            IconButton(
                onClick = {
                    isTopAppBarMenuExpanded = !isTopAppBarMenuExpanded
                }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = stringResource(R.string.tasks_menu)
                )
            }
            DropdownMenu(
                expanded = isTopAppBarMenuExpanded,
                onDismissRequest = {
                    isTopAppBarMenuExpanded = false
                }
            ) {
                DropdownMenuItem(
                    text = {
                        Text(text = stringResource(R.string.delete_all_completed))
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = stringResource(R.string.delete_all_completed_icon)
                        )
                    },
                    onClick = {
                        isTopAppBarMenuExpanded = false
                        viewModel.deleteAllCompletedTasks()
                    }
                )
            }
        }
    ) { paddingValues ->
        ListHolder(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp)
        ) {
            if (tasksList.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()

                ) {
                    items(
                        items = tasksList,
                        key = {
                            it.uid
                        }
                    ) { task ->
                        var isChecked by remember {
                            mutableStateOf(task.status)
                        }
                        TaskCard(
                            modifier = Modifier.animateItem(fadeInSpec = null, fadeOutSpec = null),
                            title = task.title,
                            isChecked = isChecked,
                            shouldStrikeThrough = true,
                            onClicked = {
                                isChecked = !isChecked
                                viewModel.setTaskStatus(task)
                            },
                            taskPriority = task.priority,
                            onLongClicked = {
                                isDeleteDialogOpen = true
                                currentSelectedTask = task
                            }
                        )
                    }
                }
            } else {
                EmptyLogo(
                    displayedString = stringResource(R.string.empty_tasks)
                )
            }
        }
    }
}
