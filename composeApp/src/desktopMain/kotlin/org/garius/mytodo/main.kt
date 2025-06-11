package org.garius.mytodo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.garius.mytodo.app.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "mytodo",
    ) {
        App()
    }
}