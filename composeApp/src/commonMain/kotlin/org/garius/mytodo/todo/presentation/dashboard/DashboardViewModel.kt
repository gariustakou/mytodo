package org.garius.mytodo.todo.presentation.dashboard

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class DashboardViewModel : ViewModel() {

    var state by mutableStateOf(DashboardState())
    private set

    private val eventChannel = Channel<DashboardEvent>()
    val events = eventChannel.receiveAsFlow()

    fun onAction(action: DashboardAction) {
        when (action) {

            else -> {}
        }
    }
}