package org.garius.mytodo.todo.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import org.garius.mytodo.core.presentation.ObserveAsEvents
import org.garius.mytodo.core.presentation.ui.LargeSpacing
import org.garius.mytodo.core.presentation.ui.MediumSpacing
import org.garius.mytodo.todo.presentation.home.components.CategoryCardsSection
import org.garius.mytodo.todo.presentation.home.components.HeaderSection
import org.garius.mytodo.todo.presentation.home.components.InProgressSection
import org.garius.mytodo.todo.presentation.home.components.TodoCountSection
import org.koin.compose.viewmodel.koinViewModel

// 5. HomeScreenRoot
@Composable
fun HomeScreenRoot(
    padding: PaddingValues = PaddingValues(0.dp),
    viewModel: HomeScreenViewModel = koinViewModel(),
    onNavigateToCategory: (String) -> Unit = {},
    onNavigateToTaskDetail: (String) -> Unit = {},
    //onNavigateToAddTask: () -> Unit = {}
) {

    val scope = rememberCoroutineScope()
//    val focusManager: FocusManager = LocalFocusManager.current
    //val snackBarState = rememberSnackBarState

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is HomeScreenEvent.Error -> {
                //scope.launch { snackBarState.showSnackbar(event.error.toString()) }
            }
            else -> {}
        }
    }

    

    HomeScreen(
        padding = padding,
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                //HomeAction.OnAddTaskClick -> onNavigateToAddTask()
                is HomeAction.OnCategoryCardClick -> onNavigateToCategory(action.category)
                is HomeAction.OnTaskProgressClick -> onNavigateToTaskDetail(action.taskId)
              else -> {}
            }
            viewModel.onAction(action)
        },
    )
}


@Composable
fun HomeScreen(
    padding: PaddingValues,
    state: HomeScreenState,

    onAction: (HomeAction) -> Unit,
) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = LargeSpacing),
            verticalArrangement = Arrangement.spacedBy(LargeSpacing)
        ) {
            item {
                Spacer(modifier = Modifier.height(MediumSpacing))
            }

            item {
                HeaderSection(
                    currentDate = state.currentDate,
                    userName = state.userName
                )
            }

            item {
                TodoCountSection(
                    todoCount = state.todoCount
                )
            }

            item {
                CategoryCardsSection(
                    categoryTasks = state.categoryTasks,
                    onCategoryClick = { category ->
                       onAction(HomeAction.OnCategoryCardClick(category.toString()))
                    }
                )
            }

            item {
                InProgressSection(
                    inProgressCount = state.inProgressCount,
                    inProgressTasks = state.inProgressTasks,
                    onTaskClick = { taskId ->
                       onAction(HomeAction.OnTaskProgressClick(taskId))
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(80.dp)) // Space for FAB
            }
        }
    }
