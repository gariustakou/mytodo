package org.garius.mytodo.app.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import org.garius.mytodo.core.navigation.Destination
import org.garius.mytodo.core.navigation.NavigationGraph
import org.garius.mytodo.todo.presentation.add.AddTaskScreenRoot
import org.garius.mytodo.todo.presentation.dashboard.DashboardScreenRoot


fun NavGraphBuilder.appNavigation(
    onNavigate: (Destination) -> Unit,
    onNavigateBack: (Destination) -> Unit,
    onGoBack: () -> Unit,
) {
    navigation<NavigationGraph.App>(startDestination = Destination.DashBoard) {
        composable<Destination.DashBoard> {
            DashboardScreenRoot(
                onNavigate = onNavigate,
            )
        }

        composable<Destination.AddTask> {
            AddTaskScreenRoot(
                onNavigateBack = onGoBack
            )
        }
    }
}

