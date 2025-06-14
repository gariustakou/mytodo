package org.garius.mytodo.todo.data.mappers

import org.garius.mytodo.todo.data.database.TaskEntity
import org.garius.mytodo.todo.domain.Task


fun Task.toTaskEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        title = title,
        description = description,
        category = category,
        priority = priority,
        status = status,
        startTime = startTime,
        endTime = endTime,
        isReminderSet = isReminderSet
    )
}

fun TaskEntity.toTask(): Task {
    return Task(
        id = id,
        title = title,
        description = description,
        category = category,
        priority = priority,
        status = status,
        startTime = startTime,
        endTime = endTime,
        isReminderSet = isReminderSet
    )
}