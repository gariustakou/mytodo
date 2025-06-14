package org.garius.mytodo.todo.data.database

import androidx.room.RoomDatabase

import androidx.room.Room
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<TaskDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "mytodo")
            os.contains("mac") -> File(userHome, "Library/Application Support/mytodo")
            else -> File(userHome, ".local/share/mytodo")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, TaskDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}