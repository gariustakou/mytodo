package org.garius.mytodo.todo.domain

data class Task(
    val id: String,
    val title: String,
    val description: String?,
    val category: String?,
    val priority: String?,
    val status: String?,
    val startTime: String?,
    val endTime: String?,
    val isReminderSet: Boolean,

)