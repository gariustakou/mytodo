package org.garius.mytodo.todo.domain.model

import androidx.compose.ui.graphics.Color
import org.garius.mytodo.core.presentation.ui.EducationCard
import org.garius.mytodo.core.presentation.ui.FloatingActionButton
import org.garius.mytodo.core.presentation.ui.ProgressRing

/**
 * Enum class representing task priority levels with associated colors for visual distinction.
 * This helps users quickly identify task importance through color coding.
 */
enum class TaskLevel(val displayName: String, val color: Color) {
    HIGH("High", FloatingActionButton),
    MEDIUM("Medium", ProgressRing),
    LOW("Low", EducationCard)
}