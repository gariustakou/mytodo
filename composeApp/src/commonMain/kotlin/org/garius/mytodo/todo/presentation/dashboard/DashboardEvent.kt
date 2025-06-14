package org.garius.mytodo.todo.presentation.dashboard



sealed interface DashboardEvent {
    data class Error(val error: String): DashboardEvent
}