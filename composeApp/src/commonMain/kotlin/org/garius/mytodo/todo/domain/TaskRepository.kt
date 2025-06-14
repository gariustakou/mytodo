package org.garius.mytodo.todo.domain

import kotlinx.coroutines.flow.Flow
import org.garius.mytodo.core.domain.DataError
import org.garius.mytodo.core.domain.EmptyResult

interface TaskRepository {
    fun getAllTasks(): Flow<List<Task>>
    suspend fun getTask(id: String): Flow<Task?>
    fun getTasksByCategory(category: String): Flow<List<Task>>
    fun getTasksByPriority(priority: String): Flow<List<Task>>
    fun getTasksByStatus(status: String): Flow<List<Task>>
    fun getTasksByStartTime(startTime: String): Flow<List<Task>>
    fun getTasksByEndTime(endTime: String): Flow<List<Task>>
    fun isReminder(id: String): Flow<Boolean>
    suspend fun addTask(task: Task): EmptyResult<DataError.Local>
    suspend fun updateTask(task: Task): EmptyResult<DataError.Local>
    //suspend fun updateTaskStatus(id: String, status: String): EmptyResult<DataError.Local>
    suspend fun deleteAllTasks()
    suspend fun deleteTask(id: String)
}