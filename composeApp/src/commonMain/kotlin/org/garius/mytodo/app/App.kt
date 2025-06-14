package org.garius.mytodo.app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import mytodo.composeapp.generated.resources.Res
import mytodo.composeapp.generated.resources.mytodo
import org.garius.mytodo.Greeting
import org.garius.mytodo.app.navigation.appNavigation
import org.garius.mytodo.app.navigation.splashNavigation
import org.garius.mytodo.core.navigation.NavigationGraph
import org.garius.mytodo.core.navigation.onGoBack
import org.garius.mytodo.core.navigation.onNavigate
import org.garius.mytodo.core.navigation.onNavigateBack
import org.garius.mytodo.core.navigation.onNavigateGraph
import org.garius.mytodo.core.presentation.ui.TodolistTheme
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    KoinContext {
        TodolistTheme {
            val navHostController = rememberNavController()
            NavHost(
                navController = navHostController,
                startDestination = NavigationGraph.Loading,
                enterTransition = { EnterTransition.None },
                exitTransition = { ExitTransition.None },
                popExitTransition = { ExitTransition.None },
                popEnterTransition = { EnterTransition.None },
            ) {
                splashNavigation(
                    onNavigateGraph = navHostController::onNavigateGraph,
                    onNavigate = navHostController::onNavigate,
                )

            appNavigation(
                onNavigate = navHostController::onNavigate,
                onNavigateBack = navHostController::onNavigateBack,
                onGoBack = navHostController::onGoBack,
            )
            }
        }
    }
}