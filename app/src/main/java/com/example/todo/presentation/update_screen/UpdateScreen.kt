package com.example.todo.presentation.update_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.todo.R
import com.example.todo.presentation.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateScreen(
    id: Int,
    mainViewModel: MainViewModel,
    onBack: () -> Unit
) {

    val taskTitle = mainViewModel.todo.taskTitle
    val task = mainViewModel.todo.task
    val isImportant = mainViewModel.todo.isImportant

    LaunchedEffect(
        key1 = true,
        block = {
            mainViewModel.getTodoById(id = id)
        }
    )

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "Update Todo",
                    style = MaterialTheme.typography.headlineSmall
                )
                
            },
                navigationIcon = {
                    IconButton(onClick = { onBack()}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = null
                        )
                        
                    }
                }
            )
        }
        
    ) {paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TextField(
                value = taskTitle,
                onValueChange = {newValue ->
                    mainViewModel.updateTaskTitle(newValue = newValue)
                },
                modifier = Modifier.fillMaxWidth(.9f),
                label = {
                    Text(
                        text ="Task Title",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Sentences
                ),
                textStyle = MaterialTheme.typography.labelLarge,
                colors = TextFieldDefaults.colors(
                    cursorColor = Color(
                        red = 37,
                        green = 113,
                        blue = 87,
                    ),
                    focusedIndicatorColor = Color(
                        red = 37,
                        green = 113,
                        blue = 87,
                    ),
                    focusedContainerColor = Color(
                        red = 207,
                        green = 231,
                        blue = 223
                    ),
                    unfocusedContainerColor = Color(
                        red = 207,
                        green = 231,
                        blue = 223
                    ),
                    disabledContainerColor = Color(
                        red = 207,
                        green = 231,
                        blue = 223
                    ),
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            TextField(
                value = task,
                onValueChange = {newValue ->
                    mainViewModel.updateTask(newValue = newValue)
                },
                modifier = Modifier.fillMaxWidth(.9f),
                label = {
                    Text(
                        text ="Task",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold
                    )
                },
                shape = RectangleShape,
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Sentences
                ),
                textStyle = MaterialTheme.typography.labelLarge,
                colors = TextFieldDefaults.colors(
                    cursorColor = Color(
                        red = 37,
                        green = 113,
                        blue = 87,
                    ),
                    focusedIndicatorColor = Color(
                        red = 37,
                        green = 113,
                        blue = 87,
                    ),
                    focusedContainerColor = Color(
                        red = 207,
                        green = 231,
                        blue = 223
                    ),
                    unfocusedContainerColor = Color(
                        red = 207,
                        green = 231,
                        blue = 223
                    ),
                    disabledContainerColor = Color(
                        red = 207,
                        green = 231,
                        blue = 223
                    ),
                )
            )

            Spacer(modifier = Modifier.size(8.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.important),
                    style = MaterialTheme.typography.labelLarge
                )
                Spacer(modifier = Modifier.size(8.dp))
                Checkbox(
                    checked = isImportant,
                    onCheckedChange = {newValue ->
                        mainViewModel.isImportant(newValue = newValue)
                    },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Color(
                            red = 15,
                            green = 77,
                            blue = 58
                        )
                    )
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Button(onClick = {
                mainViewModel.updateTodo(mainViewModel.todo)
                onBack()
            },
                colors = ButtonDefaults.buttonColors(
                    Color(
                        red = 15,
                        green = 77,
                        blue = 58
                    )
                )
            ) {
                Text(
                    text = "SaveTask",
                    fontSize = 16.sp
                )
            }
        }
        
    }
}

