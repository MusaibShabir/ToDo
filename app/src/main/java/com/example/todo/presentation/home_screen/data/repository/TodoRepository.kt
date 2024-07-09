package com.example.todo.presentation.home_screen.data.repository

import com.example.todo.presentation.home_screen.data.local.TodoDao
import com.example.todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository (
    private val dao: TodoDao
){
    suspend fun insertTodo(todo: Todo): Unit = dao.insertTodo(todo = todo)

    suspend fun updateTodo(todo: Todo): Unit = dao.updateTodo(todo = todo)

    suspend fun deleteTodo(todo: Todo): Unit = dao.deleteTodo(todo = todo)

    suspend fun getTodoById(id: Int): Todo = dao.getTodoById(id = id)

    fun getAllTodos(): Flow<List<Todo>> = dao.getAllTodos()
}