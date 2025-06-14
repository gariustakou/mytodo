package org.garius.mytodo.todo.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.datetime.Clock

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val title: String,
    val description: String?,
    val category: String?,
    val priority: String?,
    val status: String?,
    val startTime: String?,
    val endTime: String?,
    val isReminderSet: Boolean,
    @ColumnInfo(name = "created_at_timestamp")
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(),
    @ColumnInfo(name = "updated_at_timestamp")
    val updatedAt: Long = Clock.System.now().toEpochMilliseconds(),
)
