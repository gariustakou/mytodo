package org.garius.mytodo.todo.presentation.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.garius.mytodo.core.presentation.ObserveAsEvents
import org.garius.mytodo.core.presentation.ui.ExtraLargeSpacing
import org.garius.mytodo.core.presentation.ui.LargeSpacing
import org.garius.mytodo.core.presentation.ui.LightBackground
import org.garius.mytodo.core.presentation.ui.MediumSpacing
import org.garius.mytodo.core.presentation.ui.ProgressRing
import org.garius.mytodo.core.presentation.ui.TodolistTheme
import org.garius.mytodo.todo.presentation.add.components.AddTaskTopBar
import org.garius.mytodo.todo.presentation.add.components.CategorySection
import org.garius.mytodo.todo.presentation.add.components.DescriptionSection
import org.garius.mytodo.todo.presentation.add.components.LevelSection
import org.garius.mytodo.todo.presentation.add.components.ReminderSection
import org.garius.mytodo.todo.presentation.add.components.TimePickerDialog
import org.garius.mytodo.todo.presentation.add.components.TimeSection
import org.garius.mytodo.todo.presentation.add.components.TitleSection
import org.garius.mytodo.todo.presentation.home.HomeScreenEvent
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun AddTaskScreenRoot(
    onNavigateBack: () -> Unit = {},
    viewModel: AddTaskScreenViewModel = koinViewModel()
) {



    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is AddTaskScreenEvent.ShowError -> {
                //scope.launch { snackBarState.showSnackbar(event.error.toString()) }
            }
            else -> {}
        }
    }


    AddTaskScreenScreen(
        state = viewModel.state,
        onAction = { action ->
            when (action) {
                is AddTaskScreenAction.OnBackPressed -> {
                    onNavigateBack()
                }


                else -> {}
            }

            viewModel.onAction(action)

        }
    )
}

@Composable
fun AddTaskScreenScreen(
    state: AddTaskScreenState,
    onAction: (AddTaskScreenAction) -> Unit,
) {
    Scaffold(
        containerColor = LightBackground,
        topBar = {
            AddTaskTopBar(
                onBackClick = { onAction(AddTaskScreenAction.OnBackPressed) },
                onSaveClick = { onAction(AddTaskScreenAction.OnSaveTask) },
                canSave = state.isFormValid && !state.isLoading
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = LargeSpacing),
            verticalArrangement = Arrangement.spacedBy(LargeSpacing)
        ) {
            item {
                Spacer(modifier = Modifier.height(MediumSpacing))
            }

            item {
                TitleSection(
                    title = state.title,
                    titleError = state.titleError,
                    onTitleChange = { onAction(AddTaskScreenAction.OnTitleChanged(it)) }
                )
            }

            item {
                DescriptionSection(
                    description = state.description,
                    onDescriptionChange = { onAction(AddTaskScreenAction.OnDescriptionChanged(it)) }
                )
            }

            item {
                CategorySection(
                    selectedCategory = state.category,
                    isExpanded = state.isCategoryDropdownExpanded,
                    onCategorySelected = { onAction(AddTaskScreenAction.OnCategorySelected(it)) },
                    onDropdownToggle = { onAction(AddTaskScreenAction.OnCategoryDropdownToggled(it)) }
                )
            }

            item {
                LevelSection(
                    selectedLevel = state.level,
                    isExpanded = state.isLevelDropdownExpanded,
                    onLevelSelected = { onAction(AddTaskScreenAction.OnLevelSelected(it)) },
                    onDropdownToggle = { onAction(AddTaskScreenAction.OnLevelDropdownToggled(it)) }
                )
            }

            item {
                TimeSection(
                    startTime = state.startTime,
                    endTime = state.endTime,
                    timeError = state.timeError,
                    onStartTimeClick = { onAction(AddTaskScreenAction.OnStartTimePickerToggled(true)) },
                    onEndTimeClick = { onAction(AddTaskScreenAction.OnEndTimePickerToggled(true)) }
                )
            }

            item {
                ReminderSection(
                    isReminderSet = state.isReminderSet,
                    onReminderToggle = { onAction(AddTaskScreenAction.OnReminderToggled(it)) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(ExtraLargeSpacing))
            }
        }

        // Time picker dialogs
        if (state.isStartTimePickerVisible) {
            TimePickerDialog(
                onTimeSelected = { time ->
                    onAction(AddTaskScreenAction.OnStartTimeSelected(time))
                },
                onDismiss = {
                    onAction(AddTaskScreenAction.OnStartTimePickerToggled(false))
                }
            )
        }

        if (state.isEndTimePickerVisible) {
            TimePickerDialog(
                onTimeSelected = { time ->
                    onAction(AddTaskScreenAction.OnEndTimeSelected(time))
                },
                onDismiss = {
                    onAction(AddTaskScreenAction.OnEndTimePickerToggled(false))
                }
            )
        }

        // Loading overlay
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.5f))
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = ProgressRing
                )
            }
        }
    }
}



@Preview
@Composable
private fun Preview() {
    TodolistTheme {
        AddTaskScreenScreen(
            state = AddTaskScreenState(),
            onAction = {}
        )
    }
}