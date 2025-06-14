package org.garius.mytodo.todo.presentation.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.garius.mytodo.todo.presentation.dashboard.DashboardAction
import org.garius.mytodo.todo.presentation.dashboard.DashboardEvent
import org.garius.mytodo.todo.presentation.dashboard.DashboardState

class AddTaskScreenViewModel : ViewModel() {

    var state by mutableStateOf(AddTaskScreenState.initial())
        private set

    private val eventChannel = Channel<AddTaskScreenEvent>()
    val events = eventChannel.receiveAsFlow()


    fun onAction(action: AddTaskScreenAction) {
        when (action) {
            is AddTaskScreenAction.OnTitleChanged -> {
                updateTitle(action.title)
            }
            is AddTaskScreenAction.OnDescriptionChanged -> {
                updateDescription(action.description)
            }
            is AddTaskScreenAction.OnCategorySelected -> {
                updateCategory(action.category)
            }
            is AddTaskScreenAction.OnLevelSelected -> {
                updateLevel(action.level)
            }
            is AddTaskScreenAction.OnStartTimeSelected -> {
                updateStartTime(action.time)
            }
            is AddTaskScreenAction.OnEndTimeSelected -> {
                updateEndTime(action.time)
            }
            is AddTaskScreenAction.OnReminderToggled -> {
                updateReminderSetting(action.isEnabled)
            }
            is AddTaskScreenAction.OnCategoryDropdownToggled -> {
                updateCategoryDropdownState(action.isExpanded)
            }
            is AddTaskScreenAction.OnLevelDropdownToggled -> {
                updateLevelDropdownState(action.isExpanded)
            }
            is AddTaskScreenAction.OnStartTimePickerToggled -> {
                updateStartTimePickerState(action.isVisible)
            }
            is AddTaskScreenAction.OnEndTimePickerToggled -> {
                updateEndTimePickerState(action.isVisible)
            }
            is AddTaskScreenAction.OnSaveTask -> {
                saveTask()
            }
            is AddTaskScreenAction.OnCancelTask -> {
                cancelTask()
            }
//            is AddTaskScreenAction.OnBackPressed -> {
//                handleBackPress()
//            }
            else -> {}

        }


    }

    /**
     * Updates the task title and performs validation.
     * This method demonstrates how to handle text input with real-time validation.
     */
    private fun updateTitle(title: String) {
        val titleError = when {
            title.isBlank() -> "Title cannot be empty"
            title.length > 100 -> "Title must be less than 100 characters"
            else -> null
        }

        state = state.copy(
            title = title,
            titleError = titleError
        )
    }

    /**
     * Updates the task description without validation since it's optional.
     */
    private fun updateDescription(description: String) {
        state = state.copy(description = description)
    }

    /**
     * Updates the selected category and closes the dropdown.
     * This demonstrates state management for dropdown components.
     */
    private fun updateCategory(category: String) {
        state = state.copy(
            category = category,
            isCategoryDropdownExpanded = false
        )
    }

    /**
     * Updates the selected priority level and closes the dropdown.
     */
    private fun updateLevel(level: String) {
        state = state.copy(
            level = level,
            isLevelDropdownExpanded = false
        )
    }

    /**
     * Updates the start time and validates the time range.
     * This method shows how to handle complex validation involving multiple fields.
     */
    private fun updateStartTime(time: String) {
        val timeError = validateTimeRange(time, state.endTime)

        state = state.copy(
            startTime = time,
            timeError = timeError,
            isStartTimePickerVisible = false
        )
    }

    /**
     * Updates the end time and validates the time range.
     */
    private fun updateEndTime(time: String) {
        val timeError = validateTimeRange(state.startTime, time)

        state = state.copy(
            endTime = time,
            timeError = timeError,
            isEndTimePickerVisible = false
        )
    }

    /**
     * Validates that the end time is after the start time.
     * This helper method encapsulates validation logic for reusability.
     */
    private fun validateTimeRange(startTime: String, endTime: String): String? {
        if (startTime.isBlank() || endTime.isBlank()) return null

        return try {
            val startMinutes = timeToMinutes(startTime)
            val endMinutes = timeToMinutes(endTime)

            if (endMinutes <= startMinutes) {
                "End time must be after start time"
            } else null
        } catch (e: Exception) {
            "Invalid time format"
        }
    }

    /**
     * Converts time string (HH:MM) to minutes for comparison.
     * This utility method helps with time validation logic.
     */
    private fun timeToMinutes(time: String): Int {
        val parts = time.split(":")
        return parts[0].toInt() * 60 + parts[1].toInt()
    }

    /**
     * Updates the reminder setting.
     */
    private fun updateReminderSetting(isEnabled: Boolean) {
        state = state.copy(isReminderSet = isEnabled)
    }

    /**
     * Updates the category dropdown visibility state.
     */
    private fun updateCategoryDropdownState(isExpanded: Boolean) {
        state = state.copy(
            isCategoryDropdownExpanded = isExpanded,
            // Close other dropdowns when opening this one
            isLevelDropdownExpanded = if (isExpanded) false else state.isLevelDropdownExpanded
        )
    }

    /**
     * Updates the level dropdown visibility state.
     */
    private fun updateLevelDropdownState(isExpanded: Boolean) {
        state = state.copy(
            isLevelDropdownExpanded = isExpanded,
            // Close other dropdowns when opening this one
            isCategoryDropdownExpanded = if (isExpanded) false else state.isCategoryDropdownExpanded
        )
    }

    /**
     * Updates the start time picker visibility state.
     */
    private fun updateStartTimePickerState(isVisible: Boolean) {
        state = state.copy(
            isStartTimePickerVisible = isVisible,
            // Close end time picker when opening start time picker
            isEndTimePickerVisible = if (isVisible) false else state.isEndTimePickerVisible
        )
    }

    /**
     * Updates the end time picker visibility state.
     */
    private fun updateEndTimePickerState(isVisible: Boolean) {
        state = state.copy(
            isEndTimePickerVisible = isVisible,
            // Close start time picker when opening end time picker
            isStartTimePickerVisible = if (isVisible) false else state.isStartTimePickerVisible
        )
    }

    /**
     * Saves the task after validation.
     * This method demonstrates async operations and error handling in ViewModels.
     */
    private fun saveTask() {
        val currentState = state

        // Validate form before saving
        if (!currentState.isFormValid) {
            viewModelScope.launch {
                eventChannel.send(AddTaskScreenEvent.ShowError("Please fill in all required fields"))
            }
            return
        }

        viewModelScope.launch {
            state = state.copy(isLoading = true)

            try {
                // Here you would typically call a repository method to save the task
                // For now, we'll simulate a successful save operation

                // Simulate network delay
                kotlinx.coroutines.delay(1000)

                // Create task data structure
                val taskData = createTaskFromState(currentState)

                // Emit success event success
                eventChannel.send(AddTaskScreenEvent.ShowSuccess("Task created successfully"))
                //_actions.emit(AddTaskScreenAction.ShowSuccess("Task created successfully"))

                // Navigate back after successful save
                //_actions.emit(AddTaskScreenAction.NavigateBack)

            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    error = e.message
                )
                eventChannel.send(AddTaskScreenEvent.ShowError(e.message ?: "Failed to save task"))
                //_actions.emit(AddTaskScreenAction.ShowError(e.message ?: "Failed to save task"))
            }
        }
    }

    /**
     * Creates a task data structure from the current state.
     * This helper method transforms UI state into domain model data.
     */
    private fun createTaskFromState(state: AddTaskScreenState): Map<String, Any?> {
        return mapOf(
            "title" to state.title,
            "description" to state.description.takeIf { it.isNotBlank() },
            "category" to state.category.takeIf { it.isNotBlank() },
            "level" to state.level.takeIf { it.isNotBlank() },
            "status" to "pending", // Default status as specified
            "startTime" to state.startTime.takeIf { it.isNotBlank() },
            "endTime" to state.endTime.takeIf { it.isNotBlank() },
            "isReminderSet" to state.isReminderSet
        )
    }

    /**
     * Handles the cancel action.
     */
    private fun cancelTask() {
        viewModelScope.launch {
            eventChannel.send(AddTaskScreenEvent.NavigateBack)
            //_actions.emit(AddTaskScreenAction.NavigateBack)
        }
    }

    /**
     * Handles the back button press.
     */
    private fun handleBackPress() {
        viewModelScope.launch {
            eventChannel.send(AddTaskScreenEvent.NavigateBack)
            //_actions.emit(AddTaskScreenAction.NavigateBack)
        }
    }



}
