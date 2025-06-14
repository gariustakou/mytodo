package org.garius.mytodo.core.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable data object Loading : Destination
    //@Serializable data object Welcome : Destination
    @Serializable data object DashBoard : Destination
    @Serializable data object Home : Destination
    @Serializable data object AddTask : Destination


//    @Serializable data object App : Destination {
//        @Serializable data object DashBoard : Destination {
//            @Serializable data object Home : Destination
//            @Serializable data object Profile : Destination
//        }
//
//    }
}