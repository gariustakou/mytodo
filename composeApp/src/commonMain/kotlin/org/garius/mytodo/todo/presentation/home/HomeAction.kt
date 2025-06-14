package org.garius.mytodo.todo.presentation.home




sealed interface HomeAction {
    data object RefreshData : HomeAction
    data class OnCategoryCardClick(val category: String) : HomeAction
    data class OnTaskProgressClick(val taskId: String) : HomeAction
    data object OnAddTaskClick : HomeAction
    
}