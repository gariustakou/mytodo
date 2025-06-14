package org.garius.mytodo.todo.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TaskEntity::class],
    version = 3
)
//@TypeConverters(
//    TimeTypeConverter::class
//)
@ConstructedBy(TaskDatabaseConstructor::class)
abstract class TaskDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DB_NAME = "todo.db"
    }
}