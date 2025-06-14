package org.garius.mytodo.todo.presentation.splash

sealed interface SplashEvent {

    data object NavigateToHome : SplashEvent
}