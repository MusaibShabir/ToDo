package com.example.todo.presentation.home_screen.components

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import com.example.todo.R
import com.example.todo.domain.model.Todo
import com.example.todo.presentation.MainViewModel
import com.example.todo.presentation.common.toastMessage
import com.example.todo.ui.theme.ButtonColor
import com.example.todo.ui.theme.NonPrimary
import com.example.todo.ui.theme.Primary
import kotlinx.coroutines.job


@Composable
fun AlertDialog_HomeScreen(
    openDialog: Boolean,
    onClose: () -> Unit,
    mainViewModel: MainViewModel
) {
    var taskTitle by remember { mutableStateOf("") }
    var task by remember { mutableStateOf("") }
    var isImportant by remember { mutableStateOf(false) }

    val todo = Todo(0, taskTitle, task, isImportant)
    
    val focusRequester = FocusRequester()
    val context: Context = LocalContext.current
    
    if (openDialog) {
        AlertDialog(
            containerColor = NonPrimary,
            title = {
                        Text(
                            text = stringResource(id = R.string.todo_main),
                            style = MaterialTheme.typography.displaySmall
                        )
                    },
            text = {
                LaunchedEffect(key1 = true, block = {
                    coroutineContext.job.invokeOnCompletion {
                        focusRequester.requestFocus()
                    }
                })
                Column(modifier = Modifier.fillMaxWidth()){
                    TextField(
                        value = taskTitle,
                        onValueChange = {taskTitle = it},
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.add_task_title ),
                                style = MaterialTheme.typography.labelLarge


                            )
                        },
                        shape = RectangleShape,
                        modifier = Modifier.focusRequester(focusRequester),
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (taskTitle.isNotBlank()) {
                                    taskTitle = ""
                                    isImportant = false
                                    onClose()
                                } else {
                                    toastMessage(
                                        context,
                                        "Empty Task!"
                                    )
                                }
                            }
                        ),
                        trailingIcon = {
                            IconButton(onClick = { taskTitle = ""}) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = null,
                                    tint = Primary
                                )

                            }
                        },
                        textStyle = MaterialTheme.typography.labelLarge,
                        colors = TextFieldDefaults.colors(
                            cursorColor = Primary,
                            focusedIndicatorColor = Primary,
                            focusedContainerColor = NonPrimary ,
                            unfocusedContainerColor = NonPrimary,
                            disabledContainerColor = NonPrimary,
                        )


                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        value = task,
                        onValueChange = {task = it},
                        placeholder = {
                            Text(
                                text = stringResource(id = R.string.add_task),
                                style = MaterialTheme.typography.labelLarge
                            )
                        },
                        shape = RectangleShape,
                        keyboardOptions = KeyboardOptions(
                            capitalization = KeyboardCapitalization.Sentences,
                            imeAction = ImeAction.Default
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                if (task.isNotBlank()) {
                                    mainViewModel.insertTodo(todo = todo)
                                    task = ""
                                    isImportant = false
                                    onClose()
                                } else {
                                    toastMessage(
                                        context,
                                        "Empty Task!"
                                    )
                                }
                            }
                        ),
                        trailingIcon = {
                            IconButton(onClick = { task = ""}) {
                                Icon(
                                    imageVector = Icons.Rounded.Clear,
                                    contentDescription = null,
                                    tint = Primary

                                )

                            }
                        },
                        textStyle = MaterialTheme.typography.labelLarge,
                        colors = TextFieldDefaults.colors(
                            cursorColor = Primary,
                            focusedIndicatorColor = Primary,
                            focusedContainerColor = NonPrimary,
                            unfocusedContainerColor = NonPrimary,
                            disabledContainerColor = NonPrimary,
                        )

                    )

                    Row (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Text(
                            text = stringResource(id = R.string.important),
                            style = MaterialTheme.typography.labelLarge
                        )

                        Spacer(modifier = Modifier.size(8.dp))
                        Checkbox(
                            checked = isImportant ,
                            onCheckedChange = {isImportant = it},
                            colors = CheckboxDefaults.colors(
                                checkedColor = ButtonColor
                            )
                        )
                    }
                }
            },
            onDismissRequest = {
                onClose()
                taskTitle = ""
                task = ""
                isImportant = false
            },

            confirmButton = {
                Button(onClick = {
                    if (task.isNotBlank()) {
                    mainViewModel.insertTodo(todo = todo)
                    taskTitle = ""
                    task = ""
                    isImportant = false
                    onClose()
                } else {
                        toastMessage(
                        context,
                            msg = "Empty Task!"

                    )
                }
                },
                    colors = ButtonDefaults.buttonColors(ButtonColor)
                ) {
                    Text(text = stringResource(id = R.string.save ))
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    onClose()
                    taskTitle = ""
                    task = ""
                    isImportant = false
                }) {
                    Text(
                        text = stringResource(id = R.string.close),
                        color = ButtonColor
                    )
                }
            }
        )
    }
}

