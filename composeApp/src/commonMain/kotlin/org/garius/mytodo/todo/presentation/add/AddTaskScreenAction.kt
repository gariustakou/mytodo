package org.garius.mytodo.todo.presentation.add

sealed interface AddTaskScreenAction {

    // Text input events - these handle user typing in various fields
    data class OnTitleChanged(val title: String) : AddTaskScreenAction
    data class OnDescriptionChanged(val description: String) : AddTaskScreenAction

    // Selection events - these handle dropdown and picker selections
    data class OnCategorySelected(val category: String) : AddTaskScreenAction
    data class OnLevelSelected(val level: String) : AddTaskScreenAction

    // Time selection events - these handle time picker interactions
    data class OnStartTimeSelected(val time: String) : AddTaskScreenAction
    data class OnEndTimeSelected(val time: String) : AddTaskScreenAction

    // Toggle events - these handle boolean state changes
    data class OnReminderToggled(val isEnabled: Boolean) : AddTaskScreenAction

    // UI state events - these handle expanding/collapsing sections
    data class OnCategoryDropdownToggled(val isExpanded: Boolean) : AddTaskScreenAction
    data class OnLevelDropdownToggled(val isExpanded: Boolean) : AddTaskScreenAction
    data class OnStartTimePickerToggled(val isVisible: Boolean) : AddTaskScreenAction
    data class OnEndTimePickerToggled(val isVisible: Boolean) : AddTaskScreenAction

    // Action events - these handle primary user actions
    data object OnSaveTask : AddTaskScreenAction
    data object OnCancelTask : AddTaskScreenAction
    data object OnBackPressed : AddTaskScreenAction

}