package org.garius.mytodo

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.garius.mytodo.app.App
import org.garius.mytodo.app.di.initKoin


fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "mytodo",
        ) {
            App()
        }
    }
}
