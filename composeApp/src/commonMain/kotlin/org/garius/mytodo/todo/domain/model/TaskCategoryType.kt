package org.garius.mytodo.todo.domain.model

/**
 * Enum class representing different task categories.
 * This provides a predefined set of categories while allowing for easy expansion.
 */
enum class TaskCategoryType(val displayName: String) {
    WORK("Work"),
    PERSONAL("Personal"),
    SHOPPING("Shopping"),
    HEALTH("Health"),
    EDUCATION("Education"),
    ENTERTAINMENT("Entertainment")
}