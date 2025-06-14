package org.garius.mytodo.todo.presentation.add



sealed interface AddTaskScreenEvent {
    data object NavigateBack : AddTaskScreenEvent
    data class ShowError(val message: String) : AddTaskScreenEvent
    data class ShowSuccess(val message: String) : AddTaskScreenEvent
    data object ShowTimePicker : AddTaskScreenEvent
}