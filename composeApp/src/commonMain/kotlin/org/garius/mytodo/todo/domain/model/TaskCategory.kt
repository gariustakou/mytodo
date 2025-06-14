package org.garius.mytodo.todo.domain.model

import androidx.compose.ui.graphics.Color
import org.garius.mytodo.core.presentation.ui.DailyRoutinesCard
import org.garius.mytodo.core.presentation.ui.EducationCard
import org.garius.mytodo.core.presentation.ui.FreeTimeCard
import org.garius.mytodo.core.presentation.ui.WorkCard

enum class TaskCategory(
    val displayName: String,
    val backgroundColor: Color,
    val icon: String
) {
    WORK("Work", WorkCard, "👔"),
    DAILY_ROUTINES("Daily Routines", DailyRoutinesCard, "🏠"),
    FREE_TIME("Free Time", FreeTimeCard, "🎮"),
    EDUCATION("Education", EducationCard, "📚")
}