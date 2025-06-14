package org.garius.mytodo.todo.domain.model

data class TaskProgress(
    val id: String,
    val title: String,
    val remainingTime: String,
    val progressPercentage: Float,
    val isCompleted: Boolean = false
)