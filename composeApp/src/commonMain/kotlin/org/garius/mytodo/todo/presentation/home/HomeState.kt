package org.garius.mytodo.todo.presentation.home

import org.garius.mytodo.todo.domain.model.TaskCategory
import org.garius.mytodo.todo.domain.model.TaskProgress


data class HomeScreenState(
    val isLoading: Boolean = false,
    val userName: String = "Mary",
    val currentDate: String = "Tuesday, 5 Oct",
    val todoCount: Int = 0,
    val categoryTasks: Map<TaskCategory, Int> = emptyMap(),
    val inProgressTasks: List<TaskProgress> = emptyList(),
    val inProgressCount: Int = 0,
    val error: String? = null
) {
    companion object {
        fun initial() = HomeScreenState()
    }
}








