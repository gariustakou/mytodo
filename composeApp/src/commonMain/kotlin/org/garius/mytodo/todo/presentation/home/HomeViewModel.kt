package org.garius.mytodo.todo.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.toLocalDateTime
import org.garius.mytodo.todo.domain.model.TaskCategory
import org.garius.mytodo.todo.domain.model.TaskProgress


class HomeScreenViewModel(

) : ViewModel() {

    var state by mutableStateOf(HomeScreenState.initial())
    private set

    private val eventChannel = Channel<HomeScreenEvent>()
    val events = eventChannel.receiveAsFlow()



    init {
        loadInitialData()
    }



    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.RefreshData -> {
                loadInitialData()
            }
            else -> {}
        }
    }

    private fun loadInitialData() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)

            try {
                // Simulate loading data - replace with actual repository calls
                val currentDate = getCurrentFormattedDate()
                val mockCategoryTasks = mapOf(
                    TaskCategory.WORK to 4,
                    TaskCategory.DAILY_ROUTINES to 6,
                    TaskCategory.FREE_TIME to 1,
                    TaskCategory.EDUCATION to 1
                )

                val mockInProgressTasks = listOf(
                    TaskProgress(
                        id = "1",
                        title = "Create Blog post",
                        remainingTime = "2 hrs remain",
                        progressPercentage = 0.8f
                    ),
                    TaskProgress(
                        id = "2",
                        title = "Redesign Website",
                        remainingTime = "1 day remain",
                        progressPercentage = 0.4f
                    ),
                    TaskProgress(
                        id = "3",
                        title = "Redesign Website",
                        remainingTime = "1 day remain",
                        progressPercentage = 0.4f
                    ),
                    TaskProgress(
                        id = "4",
                        title = "Redesign Website",
                        remainingTime = "1 day remain",
                        progressPercentage = 0.4f
                    ),
                    TaskProgress(
                        id = "5",
                        title = "Redesign Website",
                        remainingTime = "1 day remain",
                        progressPercentage = 0.4f
                    ),
                    TaskProgress(
                        id = "6",
                        title = "Redesign Website",
                        remainingTime = "1 day remain",
                        progressPercentage = 0.4f
                    ),
                    TaskProgress(
                        id = "7",
                        title = "Redesign Website",
                        remainingTime = "1 day remain",
                        progressPercentage = 0.4f
                    ),
                    TaskProgress(
                        id = "8",
                        title = "Redesign Website",
                        remainingTime = "1 day remain",
                        progressPercentage = 0.4f
                    ),
                    TaskProgress(
                        id = "9",
                        title = "Redesign Website",
                        remainingTime = "1 day remain",
                        progressPercentage = 0.4f
                    ),
                    TaskProgress(
                        id = "10",
                        title = "Redesign Website",
                        remainingTime = "1 day remain",
                        progressPercentage = 0.4f
                    ),



                )

                state = state.copy(
                    isLoading = false,
                    currentDate = currentDate,
                    todoCount = mockCategoryTasks.values.sum(),
                    categoryTasks = mockCategoryTasks,
                    inProgressTasks = mockInProgressTasks,
                    inProgressCount = mockInProgressTasks.size,
                    error = null
                )

            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    error = e.message
                )
                eventChannel.send(HomeScreenEvent.Error(e.message ?: "Unknown error occurred"))
            }
        }
    }

    private fun getCurrentFormattedDate(): String {
        val now = Clock.System.now()
        val localDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
        val dayOfWeek = when (localDateTime.dayOfWeek.isoDayNumber) {
            1 -> "Monday"
            2 -> "Tuesday"
            3 -> "Wednesday"
            4 -> "Thursday"
            5 -> "Friday"
            6 -> "Saturday"
            7 -> "Sunday"
            else -> "Unknown"
        }
        val month = when (localDateTime.monthNumber) {
            1 -> "Jan"
            2 -> "Feb"
            3 -> "Mar"
            4 -> "Apr"
            5 -> "May"
            6 -> "Jun"
            7 -> "Jul"
            8 -> "Aug"
            9 -> "Sep"
            10 -> "Oct"
            11 -> "Nov"
            12 -> "Dec"
            else -> "Unknown"
        }
        println("Day of the week: $dayOfWeek Month: $month Day: ${localDateTime.dayOfMonth}")
        return "$dayOfWeek, ${localDateTime.dayOfMonth} $month"
    }
}



