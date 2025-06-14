package org.garius.mytodo.todo.presentation.home

sealed interface HomeScreenEvent {
    data class Error(val error: String): HomeScreenEvent
}