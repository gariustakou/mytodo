package org.garius.mytodo.core.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun TodolistTheme(
    darkTheme: Boolean = false, // ou isSystemInDarkTheme()
    content: @Composable () -> Unit
) {
    val colors = if (!darkTheme) LightColorScheme else DarkColorScheme
    MaterialTheme(
        colorScheme = colors,
        typography = AppTypography(),
        shapes = Shapes,
        content = content
    )
}
