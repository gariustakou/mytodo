@file:OptIn(ExperimentalMaterial3Api::class)

package org.garius.mytodo.todo.presentation.add.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import mytodo.composeapp.generated.resources.Res
import mytodo.composeapp.generated.resources.todo
import org.garius.mytodo.core.presentation.ui.FloatingActionButton
import org.garius.mytodo.core.presentation.ui.LightBackground
import org.garius.mytodo.core.presentation.ui.LargeSpacing
import org.garius.mytodo.core.presentation.ui.MediumSpacing
import org.garius.mytodo.core.presentation.ui.PrimaryText
import org.garius.mytodo.core.presentation.ui.ProgressRing
import org.garius.mytodo.core.presentation.ui.SecondaryText
import org.garius.mytodo.core.presentation.ui.SmallSpacing
import org.garius.mytodo.todo.domain.model.TaskCategoryType
import org.garius.mytodo.todo.domain.model.TaskLevel
import org.jetbrains.compose.resources.painterResource


@Composable
 fun AddTaskTopBar(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    canSave: Boolean
) {
    TopAppBar(
        title = {
            Text(
                text = "Add New Task",
                style = MaterialTheme.typography.headlineLarge,
                color = PrimaryText
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    //imageVector = Icons.Default.ArrowBack,
                    painter = painterResource(Res.drawable.todo),
                    contentDescription = "Back",
                    tint = PrimaryText
                )
            }
        },
        actions = {
            TextButton(
                onClick = onSaveClick,
                enabled = canSave
            ) {
                Text(
                    text = "Save",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = if (canSave) ProgressRing else SecondaryText
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = LightBackground
        )
    )
}

/**
 * Title input section with validation error display.
 * This demonstrates form field validation and error handling.
 */
@Composable
fun TitleSection(
    title: String,
    titleError: String?,
    onTitleChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SmallSpacing)
    ) {
        Text(
            text = "Task Title",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryText
        )

        OutlinedTextField(
            value = title,
            onValueChange = onTitleChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Enter task title",
                    color = SecondaryText
                )
            },
            isError = titleError != null,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ProgressRing,
                unfocusedBorderColor = SecondaryText.copy(alpha = 0.3f),
                errorBorderColor = FloatingActionButton
            ),
            shape = RoundedCornerShape(MediumSpacing)
        )

        // Error message display
        AnimatedVisibility(
            visible = titleError != null,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            titleError?.let { error ->
                Text(
                    text = error,
                    style = MaterialTheme.typography.labelSmall,
                    color = FloatingActionButton,
                    modifier = Modifier.padding(start = MediumSpacing)
                )
            }
        }
    }
}

/**
 * Description input section for optional task description.
 */
@Composable
 fun DescriptionSection(
    description: String,
    onDescriptionChange: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SmallSpacing)
    ) {
        Text(
            text = "Description (Optional)",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryText
        )

        OutlinedTextField(
            value = description,
            onValueChange = onDescriptionChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            placeholder = {
                Text(
                    text = "Add task description...",
                    color = SecondaryText
                )
            },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ProgressRing,
                unfocusedBorderColor = SecondaryText.copy(alpha = 0.3f)
            ),
            shape = RoundedCornerShape(MediumSpacing)
        )
    }
}

/**
 * Category selection section with expandable dropdown.
 * This demonstrates custom dropdown implementation with smooth animations.
 */
@Composable
 fun CategorySection(
    selectedCategory: String,
    isExpanded: Boolean,
    onCategorySelected: (String) -> Unit,
    onDropdownToggle: (Boolean) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SmallSpacing)
    ) {
        Text(
            text = "Category",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryText
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onDropdownToggle(!isExpanded) },
            shape = RoundedCornerShape(MediumSpacing),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LargeSpacing),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = selectedCategory.ifBlank { "Select category" },
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (selectedCategory.isBlank()) SecondaryText else PrimaryText
                )

                Icon(
                    //imageVector = Icons.Default.ArrowDropDown,
                    painter = painterResource(Res.drawable.todo),
                    contentDescription = null,
                    tint = SecondaryText,
                    modifier = Modifier.rotate(if (isExpanded) 180f else 0f)
                        .size(20.dp)
                )
            }
        }

        // Animated dropdown content
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(MediumSpacing),
                color = Color.White,
                shadowElevation = 4.dp
            ) {
                Column {
                    TaskCategoryType.values().forEach { category ->
                        DropdownItem(
                            text = category.displayName,
                            isSelected = selectedCategory == category.displayName,
                            onClick = { onCategorySelected(category.displayName) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Priority level selection section with color-coded options.
 * This section demonstrates how to create a visually appealing dropdown
 * that uses different colors to represent different priority levels.
 */
@Composable
 fun LevelSection(
    selectedLevel: String,
    isExpanded: Boolean,
    onLevelSelected: (String) -> Unit,
    onDropdownToggle: (Boolean) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SmallSpacing)
    ) {
        Text(
            text = "Priority Level",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryText
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onDropdownToggle(!isExpanded) },
            shape = RoundedCornerShape(MediumSpacing),
            color = Color.White,
            shadowElevation = 2.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LargeSpacing),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
                ) {
                    // Show priority level indicator dot if a level is selected
                    if (selectedLevel.isNotBlank()) {
                        Box(
                            modifier = Modifier
                                .size(12.dp)
                                .background(
                                    color = getPriorityColor(selectedLevel),
                                    shape = androidx.compose.foundation.shape.CircleShape
                                )
                        )
                    }

                    Text(
                        text = selectedLevel.ifBlank { "Select priority level" },
                        style = MaterialTheme.typography.bodyMedium,
                        color = if (selectedLevel.isBlank()) SecondaryText else PrimaryText
                    )
                }

                Icon(
                    //imageVector = Icons.Default.ArrowDropDown,
                    painter = painterResource(Res.drawable.todo),
                    contentDescription = null,
                    tint = SecondaryText,
                    modifier = Modifier.rotate(if (isExpanded) 180f else 0f)
                        .size(20.dp)
                )
            }
        }

        // Animated dropdown content with priority level options
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(MediumSpacing),
                color = Color.White,
                shadowElevation = 4.dp
            ) {
                Column {
                    TaskLevel.values().forEach { level ->
                        PriorityDropdownItem(
                            level = level,
                            isSelected = selectedLevel == level.displayName,
                            onClick = { onLevelSelected(level.displayName) }
                        )
                    }
                }
            }
        }
    }
}

/**
 * Time selection section that allows users to set start and end times.
 * This component shows how to create clickable time selectors with validation.
 */
@Composable
 fun TimeSection(
    startTime: String,
    endTime: String,
    timeError: String?,
    onStartTimeClick: () -> Unit,
    onEndTimeClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SmallSpacing)
    ) {
        Text(
            text = "Time Range",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = PrimaryText
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
        ) {
            // Start time selector
            TimeSelector(
                modifier = Modifier.weight(1f),
                label = "Start Time",
                time = startTime,
                onClick = onStartTimeClick,
                hasError = timeError != null
            )

            // Visual separator
            Box(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(24.dp)
                    .height(2.dp)
                    .background(
                        color = SecondaryText.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(1.dp)
                    )
            )

            // End time selector
            TimeSelector(
                modifier = Modifier.weight(1f),
                label = "End Time",
                time = endTime,
                onClick = onEndTimeClick,
                hasError = timeError != null
            )
        }

        // Time validation error display
        AnimatedVisibility(
            visible = timeError != null,
            enter = expandVertically() + fadeIn(),
            exit = shrinkVertically() + fadeOut()
        ) {
            timeError?.let { error ->
                Text(
                    text = error,
                    style = MaterialTheme.typography.labelSmall,
                    color = FloatingActionButton,
                    modifier = Modifier.padding(start = MediumSpacing)
                )
            }
        }
    }
}

/**
 * Individual time selector component that shows the selected time
 * and provides a clickable interface to open the time picker.
 */
@Composable
 fun TimeSelector(
    modifier: Modifier = Modifier,
    label: String,
    time: String,
    onClick: () -> Unit,
    hasError: Boolean = false
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(SmallSpacing)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = SecondaryText
        )

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() },
            shape = RoundedCornerShape(MediumSpacing),
            color = Color.White,
            shadowElevation = if (hasError) 0.dp else 1.dp,
            border = if (hasError)
                androidx.compose.foundation.BorderStroke(1.dp, FloatingActionButton)
            else null
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(LargeSpacing),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = time.ifBlank { "Select time" },
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (time.isBlank()) SecondaryText else PrimaryText
                )

                Icon(
                    //imageVector = Icons.Default.Schedule,
                    painter = painterResource(Res.drawable.todo),
                    contentDescription = null,
                    tint = ProgressRing,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

/**
 * Reminder toggle section that allows users to enable/disable task reminders.
 * This demonstrates how to create an attractive toggle switch with proper feedback.
 */
@Composable
 fun ReminderSection(
    isReminderSet: Boolean,
    onReminderToggle: (Boolean) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(MediumSpacing),
        color = Color.White,
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LargeSpacing),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
            ) {
                Icon(
                    //imageVector = Icons.Default.Notifications,
                    painter = painterResource(Res.drawable.todo),
                    contentDescription = null,
                    tint = if (isReminderSet) ProgressRing else SecondaryText,
                    modifier = Modifier.size(24.dp)
                )

                Column {
                    Text(
                        text = "Set Reminder",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryText
                    )

                    Text(
                        text = if (isReminderSet) "Reminder enabled" else "No reminder set",
                        style = MaterialTheme.typography.labelSmall,
                        color = SecondaryText
                    )
                }
            }

            Switch(
                checked = isReminderSet,
                onCheckedChange = onReminderToggle,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = ProgressRing,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = SecondaryText.copy(alpha = 0.3f)
                )
            )
        }
    }
}

/**
 * Generic dropdown item component used in category and level dropdowns.
 * This creates a consistent look and feel across all dropdown options.
 */
@Composable
 fun DropdownItem(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        color = if (isSelected) ProgressRing.copy(alpha = 0.1f) else Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LargeSpacing),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                color = if (isSelected) ProgressRing else PrimaryText
            )

            if (isSelected) {
                Icon(
                    //imageVector = Icons.Default.Check,
                    painter = painterResource(Res.drawable.todo),
                    contentDescription = null,
                    tint = ProgressRing,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

/**
 * Specialized dropdown item for priority levels that includes color indicators.
 * This shows how to create more complex dropdown items with visual enhancements.
 */
@Composable
 fun PriorityDropdownItem(
    level: TaskLevel,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        color = if (isSelected) getPriorityColor(level.displayName).copy(alpha = 0.1f) else Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LargeSpacing),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
            ) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .background(
                            color = getPriorityColor(level.displayName),
                            shape = androidx.compose.foundation.shape.CircleShape
                        )
                )

                Text(
                    text = level.displayName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = if (isSelected) getPriorityColor(level.displayName) else PrimaryText
                )
            }

            if (isSelected) {
                Icon(
                    //imageVector = Icons.Default.Check,
                    painter = painterResource(Res.drawable.todo),
                    contentDescription = null,
                    tint = getPriorityColor(level.displayName),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

/**
 * Time picker dialog component that provides a user-friendly interface
 * for selecting time values. This demonstrates how to create custom dialogs
 * that integrate with the Material 3 design system.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun TimePickerDialog(
    onTimeSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val timePickerState = rememberTimePickerState()

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    val hour = timePickerState.hour.toString().padStart(2, '0')
                    val minute = timePickerState.minute.toString().padStart(2, '0')
                    onTimeSelected("$hour:$minute")
                }
            ) {
                Text(
                    text = "OK",
                    color = ProgressRing,
                    fontWeight = FontWeight.SemiBold
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(
                    text = "Cancel",
                    color = SecondaryText
                )
            }
        },
        title = {
            Text(
                text = "Select Time",
                style = MaterialTheme.typography.headlineLarge,
                color = PrimaryText
            )
        },
        text = {
            TimePicker(
                state = timePickerState,
                colors = TimePickerDefaults.colors(
                    clockDialColor = LightBackground,
                    selectorColor = ProgressRing,
                    containerColor = Color.White,
                    periodSelectorBorderColor = ProgressRing,
                    clockDialSelectedContentColor = Color.White,
                    clockDialUnselectedContentColor = PrimaryText,
                    periodSelectorSelectedContainerColor = ProgressRing,
                    periodSelectorUnselectedContainerColor = LightBackground,
                    periodSelectorSelectedContentColor = Color.White,
                    periodSelectorUnselectedContentColor = PrimaryText,
                    timeSelectorSelectedContainerColor = ProgressRing,
                    timeSelectorUnselectedContainerColor = LightBackground,
                    timeSelectorSelectedContentColor = Color.White,
                    timeSelectorUnselectedContentColor = PrimaryText
                )
            )
        },
        containerColor = Color.White,
        shape = RoundedCornerShape(LargeSpacing)
    )
}

/**
 * Utility function that returns the appropriate color for each priority level.
 * This helps maintain consistency in color usage throughout the application.
 */
@Composable
 fun getPriorityColor(level: String): Color {
    return when (level.lowercase()) {
        "high" -> FloatingActionButton
        "medium" -> Color(0xFFFF9800) // Orange color for medium priority
        "low" -> Color(0xFF4CAF50) // Green color for low priority
        else -> SecondaryText
    }
}