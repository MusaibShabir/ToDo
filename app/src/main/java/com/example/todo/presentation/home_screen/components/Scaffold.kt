package com.example.todo.presentation.home_screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.todo.presentation.MainViewModel
import com.example.todo.presentation.common.mySnackbar

@Composable
fun Home_Scaffold(
    mainViewModel: MainViewModel,
    onUpdate: (id: Int) -> Unit
) {
    val todos by mainViewModel.getAllTodos.collectAsStateWithLifecycle(initialValue = emptyList())

    var openDialog by rememberSaveable { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = { AppNameCard() },
        bottomBar = { BottomButton { openDialog = true } },
    ) { paddingValues ->
        AlertDialog_HomeScreen(
            openDialog = openDialog,
            onClose = { openDialog = false },
            mainViewModel = mainViewModel
        )

        if (todos.isEmpty()) {
            EmptyTaskScreen(paddingValues = paddingValues)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = todos,
                    key = { it.id }
                ) { todo ->
                    TodoCard(
                        todo = todo,
                        onDone = {
                            mainViewModel.deleteTodo(todo = todo)
                            mySnackbar(
                                scope = scope,
                                snackbarHostState = snackbarHostState,
                                msg = "DONE -> \${todo.task}\"",
                                actionLabel = "UNDO",
                                onAction = { mainViewModel.undoDeletedTodo() }
                            )
                        },
                        onUpdate = onUpdate,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}