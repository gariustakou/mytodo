package org.garius.mytodo.todo.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mytodo.composeapp.generated.resources.Res
import mytodo.composeapp.generated.resources.todo
import org.garius.mytodo.core.presentation.ui.FloatingActionButton
import org.garius.mytodo.core.presentation.ui.InProgressBackground
import org.garius.mytodo.core.presentation.ui.LargeSpacing
import org.garius.mytodo.core.presentation.ui.MediumSpacing
import org.garius.mytodo.core.presentation.ui.PrimaryText
import org.garius.mytodo.core.presentation.ui.ProgressRing
import org.garius.mytodo.core.presentation.ui.SecondaryText
import org.garius.mytodo.core.presentation.ui.SmallSpacing
import org.garius.mytodo.todo.domain.model.TaskCategory
import org.garius.mytodo.todo.domain.model.TaskProgress
import org.jetbrains.compose.resources.painterResource

@Composable
fun HeaderSection(
    currentDate: String,
    userName: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SmallSpacing)
    ) {
        Text(
            text = currentDate,
            style = MaterialTheme.typography.headlineLarge,
            color = PrimaryText,
            fontWeight = FontWeight.SemiBold
        )

        Text(
            text = "Hello $userName! 👋",
            style = MaterialTheme.typography.bodyMedium,
            color = SecondaryText
        )
    }
}

@Composable
fun TodoCountSection(
    todoCount: Int
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "To Do",
            style = MaterialTheme.typography.bodyLarge,
            color = PrimaryText,
            fontWeight = FontWeight.Medium
        )

        Surface(
            shape = CircleShape,
            color = FloatingActionButton,
            modifier = Modifier.size(24.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = todoCount.toString(),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontSize = 10.sp
                )
            }
        }
    }
}

@Composable
fun CategoryCardsSection(
    categoryTasks: Map<TaskCategory, Int>,
    onCategoryClick: (TaskCategory) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
        items(
            items = TaskCategory.entries,      // <-- named parameter ici
            key = { category -> category.name }          // facultatif : une clé unique
        ) { category ->
            val taskCount = categoryTasks[category] ?: 0
            CategoryCard(
                category = category,
                taskCount = taskCount,
                onClick = { onCategoryClick(category) }
            )
        }
    }
}


@Composable
fun CategoryCard(
    category: TaskCategory,
    taskCount: Int,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(120.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(LargeSpacing),
        color = category.backgroundColor
    ) {
        Column(
            modifier = Modifier.padding(MediumSpacing),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Text(
                    text = category.icon,
                    fontSize = 20.sp
                )

                Icon(
                    painter = painterResource(Res.drawable.todo),
                    contentDescription = null,
                    tint = PrimaryText,
                    modifier = Modifier.size(16.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                Text(
                    text = category.displayName,
                    style = MaterialTheme.typography.bodyMedium,
                    color = PrimaryText,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "$taskCount Task${if (taskCount != 1) "s" else ""}",
                    style = MaterialTheme.typography.labelSmall,
                    color = SecondaryText
                )
            }
        }
    }
}

@Composable
fun InProgressSection(
    inProgressCount: Int,
    inProgressTasks: List<TaskProgress>,
    onTaskClick: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(MediumSpacing)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "In Progress",
                style = MaterialTheme.typography.bodyLarge,
                color = PrimaryText,
                fontWeight = FontWeight.Medium
            )

            Surface(
                shape = CircleShape,
                color = FloatingActionButton,
                modifier = Modifier.size(24.dp)
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = inProgressCount.toString(),
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White,
                        fontSize = 10.sp
                    )
                }
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(MediumSpacing)
        ) {
            inProgressTasks.forEach { task ->
                TaskProgressCard(
                    task = task,
                    onClick = { onTaskClick(task.id) }
                )
            }
        }
    }
}

@Composable
fun TaskProgressCard(
    task: TaskProgress,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(LargeSpacing),
        color = InProgressBackground
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(LargeSpacing),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(SmallSpacing)
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyMedium,
                    color = PrimaryText,
                    fontWeight = FontWeight.Medium
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(SmallSpacing)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.todo),
                        contentDescription = null,
                        tint = SecondaryText,
                        modifier = Modifier.size(14.dp)
                    )

                    Text(
                        text = task.remainingTime,
                        style = MaterialTheme.typography.labelSmall,
                        color = SecondaryText
                    )
                }
            }

            CircularProgressIndicator(
            progress = { task.progressPercentage },
            modifier = Modifier.size(48.dp),
            color = ProgressRing,
            strokeWidth = 4.dp,
            trackColor = Color.White,
            //strokeCap = COMPILED_CODE,
            )
        }
    }
}