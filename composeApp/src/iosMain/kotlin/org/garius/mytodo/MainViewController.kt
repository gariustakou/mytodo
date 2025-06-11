package org.garius.mytodo

import androidx.compose.ui.window.ComposeUIViewController
import org.garius.mytodo.app.App
import  org.garius.mytodo.app.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {

    App(

    )
}