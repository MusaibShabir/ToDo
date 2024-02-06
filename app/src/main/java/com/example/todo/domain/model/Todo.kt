package com.example.todo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//Room Database Table
@Entity
data class Todo(

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val taskTitle : String,
    val task : String,
    val isImportant : Boolean

)
