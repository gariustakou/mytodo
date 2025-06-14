package org.garius.mytodo.todo.presentation.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SplashViewModel (

) : ViewModel() {

    var state by mutableStateOf(SplashState())
        private set

    private val eventChannel = Channel<SplashEvent>()
    val events = eventChannel.receiveAsFlow()


    fun onAction(action: SplashAction) {
        when (action) {
            is SplashAction.Initialize -> initialize()
        }
    }

    private fun initialize() {
        viewModelScope.launch {
            try {

                // Update state
               state = state.copy(
                    isLoading = true,
                    error = null
                )

                // Determine navigation path
               eventChannel.send(SplashEvent.NavigateToHome)
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }






}