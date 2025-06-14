package org.garius.mytodo.core.navigation

import kotlinx.serialization.Serializable

sealed interface NavigationGraph {
    @Serializable data object Loading : NavigationGraph
    @Serializable data object App : NavigationGraph
}