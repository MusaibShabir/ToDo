package com.example.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todo.domain.model.Todo

@Database(entities = [Todo::class], version = 3, exportSchema = true)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao() : TodoDao

    /* Migration Code
    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(db: SupportSQLiteDatabase) {
                db.execSQL("ALTER TABLE Todo ADD COLUMN taskTitle TEXT")
            }
        }
    }

     */
}

