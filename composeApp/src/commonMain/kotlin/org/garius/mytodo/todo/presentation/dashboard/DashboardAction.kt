package org.garius.mytodo.todo.presentation.dashboard

sealed interface DashboardAction {
    data object RefreshData : DashboardAction
    data class OnCategoryCardClick(val category: String) : DashboardAction
    data class OnTaskProgressClick(val taskId: String) : DashboardAction
    data object OnAddTaskClick : DashboardAction

}