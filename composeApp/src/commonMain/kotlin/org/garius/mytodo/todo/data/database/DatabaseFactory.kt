package org.garius.mytodo.todo.data.database

import androidx.room.RoomDatabase


expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<TaskDatabase>
}