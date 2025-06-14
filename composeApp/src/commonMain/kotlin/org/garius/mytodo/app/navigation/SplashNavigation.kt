package org.garius.mytodo.app.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.garius.mytodo.core.navigation.Destination
import org.garius.mytodo.core.navigation.NavigationGraph
import org.garius.mytodo.todo.presentation.splash.SplashScreenRoot


fun NavGraphBuilder.splashNavigation(
    onNavigate: (Destination, Boolean) -> Unit,
    onNavigateGraph: (NavigationGraph, Boolean) -> Unit,
) {
    navigation<NavigationGraph.Loading>(startDestination = Destination.Loading) {
        composable<Destination.Loading> {
            SplashScreenRoot(
                onNavigateToHome = {
                    onNavigateGraph(NavigationGraph.App, true)
                },
            )
        }
    }
}