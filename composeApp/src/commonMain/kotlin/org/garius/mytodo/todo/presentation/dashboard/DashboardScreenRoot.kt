package org.garius.mytodo.todo.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import mytodo.composeapp.generated.resources.Res
import mytodo.composeapp.generated.resources.todo
import org.garius.mytodo.core.navigation.Destination
import org.garius.mytodo.core.navigation.isCurrentRoute
import org.garius.mytodo.core.presentation.ObserveAsEvents
import org.garius.mytodo.core.presentation.ui.LightBackground
import org.garius.mytodo.todo.presentation.dashboard.components.BottomNavigationBar
import org.garius.mytodo.todo.presentation.home.HomeScreenRoot
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DashboardScreenRoot(
    onNavigate: (Destination) -> Unit,
    viewModel: DashboardViewModel = koinViewModel()
) {


    val scope = rememberCoroutineScope()
    val navigator = rememberNavController()
    val navBackStackEntry by navigator.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val hierarchy = currentDestination?.hierarchy
    //val snackBarHostState = rememberSnackBarHostState()


    // Observe ViewModel events
    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is DashboardEvent.Error -> {
               // scope.launch { snackBarHostState.showSnackbar(event.error) }
            }
        }
    }


        // Centralized navigation handler for bottom navigation
        fun onNavigateBottomNavigation(destination: Destination) {
            if (!hierarchy.isCurrentRoute(destination)) {
                navigator.navigate(destination)
            }
            //viewModel.onAction(DashboardAction.HideCentral)
        }

        // Centralized event handling
        fun onAction(action: DashboardAction) {
            when (action) {
                is DashboardAction.OnCategoryCardClick -> {
                    //onNavigate(Destination.Category(action.category))
                }
//                is DashboardAction.OnAddTaskClick -> {
//                    //onNavigate(Destination.TaskDetail(action.taskId))
//                }
                is DashboardAction.OnTaskProgressClick -> {
                }

                else -> {}
            }
        }

        Scaffold(
            containerColor = LightBackground,
            bottomBar = {
                BottomNavigationBar()
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { onNavigate(Destination.AddTask) },
                    containerColor = FloatingActionButtonDefaults.containerColor,
                    contentColor = Color.White,
                    shape = CircleShape,
                    modifier = Modifier.size(60.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.todo),
                        contentDescription = "Add Task"
                    )
                }
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { padding ->
            Box(modifier = Modifier.fillMaxSize()) {
                // Main navigation content
                NavHost(
                    modifier = Modifier.run {
                        this
                    },
                    navController = navigator,
                    startDestination = Destination.Home
                ) {
                    composable<Destination.Home> {
                        HomeScreenRoot(
                            padding = padding,
                            onNavigateToCategory = {

                            },
                            onNavigateToTaskDetail = { taskId ->
                                //onNavigate(Destination.TaskDetail(taskId))
                            },
//                            onNavigateToAddTask = {
//                                //onNavigate(Destination.AddTask)
//                            },
                        )
                    }
                    //composable<Destination.Profile> {
                    //    ProfileScreenRoot(
                    //        padding = padding,
                     //
                    //    )
                    //}
                }

            }
        }
    }



