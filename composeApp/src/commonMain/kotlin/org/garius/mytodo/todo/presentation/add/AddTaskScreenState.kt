package org.garius.mytodo.todo.presentation.add

data class AddTaskScreenState(
    // Core task data - these represent the actual task properties
    val title: String = "",
    val description: String = "",
    val category: String = "",
    val level: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val isReminderSet: Boolean = false,

    // UI state - these control the visual appearance and behavior of UI components
    val isCategoryDropdownExpanded: Boolean = false,
    val isLevelDropdownExpanded: Boolean = false,
    val isStartTimePickerVisible: Boolean = false,
    val isEndTimePickerVisible: Boolean = false,

    // Validation state - these help provide user feedback about form validity
    val titleError: String? = null,
    val timeError: String? = null,

    // Loading and error states - these handle async operations and error display
    val isLoading: Boolean = false,
    val error: String? = null
) {
    companion object {
        /**
         * Factory method to create the initial state.
         * This centralizes the default values and makes testing easier.
         */
        fun initial() = AddTaskScreenState()
    }

    /**
     * Computed property to determine if the form is valid for submission.
     * This encapsulates validation logic within the state itself.
     */
    val isFormValid: Boolean
        get() = title.isNotBlank() &&
                titleError == null &&
                timeError == null
}