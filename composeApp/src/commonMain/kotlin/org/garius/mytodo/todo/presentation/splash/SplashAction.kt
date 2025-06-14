package org.garius.mytodo.todo.presentation.splash

sealed interface SplashAction {
    data object Initialize : SplashAction
}