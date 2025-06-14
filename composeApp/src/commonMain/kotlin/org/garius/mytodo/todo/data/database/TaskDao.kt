package org.garius.mytodo.todo.data.database

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Upsert
    suspend fun upsert(book: TaskEntity)

    @Query("SELECT * FROM TaskEntity ORDER BY created_at_timestamp ASC")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    fun getTask(id: String): Flow<TaskEntity?>

    @Query("DELETE FROM TaskEntity WHERE id = :id")
    suspend fun deleteTask(id: String)

    @Query("DELETE FROM TaskEntity")
    suspend fun deleteAllTasks()

    @Query("SELECT * FROM TaskEntity WHERE category = :category")
    fun getTasksByCategory(category: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE priority = :priority")
    fun getTasksByPriority(priority: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE status = :status")
    fun getTasksByStatus(status: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE startTime = :startTime")
    fun getTasksByStartTime(startTime: String): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE endTime = :endTime")
    fun getTasksByEndTime(endTime: String): Flow<List<TaskEntity>>

    @Query("SELECT isReminderSet FROM TaskEntity WHERE id = :id")
    fun isReminder(id: String): Flow<Boolean>


}