package org.garius.mytodo.todo.data.repository

import androidx.sqlite.SQLiteException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.garius.mytodo.core.domain.DataError
import org.garius.mytodo.core.domain.EmptyResult
import org.garius.mytodo.todo.data.database.TaskDao
import org.garius.mytodo.todo.data.mappers.toTask
import org.garius.mytodo.todo.data.mappers.toTaskEntity
import org.garius.mytodo.todo.domain.Task
import org.garius.mytodo.todo.domain.TaskRepository
import org.garius.mytodo.core.domain.Result


class ImplTaskRepository(
    private val taskDao: TaskDao
): TaskRepository {

     override fun getAllTasks(): Flow<List<Task>> {
         return taskDao
             .getAllTasks()
             .map { taskEntities ->
                 taskEntities.map { it.toTask() }
             }
     }

    override suspend fun getTask(id: String): Flow<Task?> {
        return taskDao.getTask(id)
            .map { taskEntity ->
                // Transform TaskEntity? to Task?
                // If taskEntity is null, the result will be null
                // If taskEntity exists, convert it to Task using your mapper
                taskEntity?.toTask()
            }
    }

    override fun getTasksByCategory(category: String): Flow<List<Task>> {
       return taskDao.getTasksByCategory(category)
           .map { taskEntities ->
               taskEntities.map { it.toTask() }
           }
    }

    override fun getTasksByStatus(status: String): Flow<List<Task>> {
       return taskDao.getTasksByStatus(status)
           .map { taskEntities ->
               taskEntities.map { it.toTask() }
           }
    }


    override fun getTasksByPriority(priority: String): Flow<List<Task>> {
       return taskDao.getTasksByPriority(priority)
           .map { taskEntities ->
               taskEntities.map { it.toTask() }
           }
    }

    override fun getTasksByStartTime(startTime: String): Flow<List<Task>> {
       return taskDao.getTasksByStartTime(startTime)
           .map { taskEntities ->
               taskEntities.map { it.toTask() }
           }
    }

    override fun getTasksByEndTime(endTime: String): Flow<List<Task>> {
        return taskDao.getTasksByEndTime(endTime)
            .map { taskEntities ->
                taskEntities.map { it.toTask() }
            }
    }

    override fun isReminder(id: String): Flow<Boolean> {
        return taskDao.isReminder(id)
    }

    override suspend fun addTask(task: Task): EmptyResult<DataError.Local> {
       return try {
           taskDao.upsert(task.toTaskEntity())
           Result.Success(Unit)
       } catch(e: SQLiteException) {
           Result.Error(DataError.Local.DISK_FULL)
       }
    }

    override suspend fun updateTask(task: Task): EmptyResult<DataError.Local> {
        return try {
            taskDao.upsert(task.toTaskEntity())
            Result.Success(Unit)
        } catch (e: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

        override suspend fun deleteAllTasks() {
            taskDao.deleteAllTasks()
        }

    override suspend fun deleteTask(id: String) {
        taskDao.deleteTask(id)
    }



























}