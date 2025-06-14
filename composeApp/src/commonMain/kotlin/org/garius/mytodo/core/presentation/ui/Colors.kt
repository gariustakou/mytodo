package org.garius.mytodo.core.presentation.ui

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

// Palette principale (Light)
val LightBackground        = Color(0xFFF9FBFC)
val WorkCard               = Color(0xFFD6F7FD)
val DailyRoutinesCard      = Color(0xFFFCD6D9)
val FreeTimeCard           = Color(0xFFE8DEFF)
val EducationCard          = Color(0xFFB9FFE5)
val InProgressBackground   = Color(0xFFE8F8FA)
val ProgressRing           = Color(0xFF4BCFFA)
val FloatingActionButton   = Color(0xFFFF6B6B)
val DateSelectionBackground= Color(0xFFD2F1FF)
val TimelineLine           = Color(0xFFFFC6C6)
val DoneBanner             = Color(0xFFE0F7FA)
val MeetingCard            = Color(0xFFDDEFFF)
val WebinarCard            = Color(0xFFDFFEEA)

// Textes
val PrimaryText            = Color(0xFF333333)
val SecondaryText          = Color(0xFF555555)

// Light ColorScheme
internal val LightColorScheme = lightColorScheme(
    primary       = FloatingActionButton,
    secondary     = ProgressRing,
    background    = LightBackground,
    surface       = Color.White,
    onPrimary     = Color.White,
    onSecondary   = Color.White,
    onBackground  = PrimaryText,
    onSurface     = PrimaryText
)

// Dark ColorScheme (version simplifiée)
internal val DarkColorScheme = darkColorScheme(
    primary       = FloatingActionButton,
    secondary     = ProgressRing,
    background    = Color(0xFF121212),
    surface       = Color(0xFF1E1E1E),
    onPrimary     = Color.White,
    onSecondary   = Color.White,
    onBackground  = Color(0xFFE0E0E0),
    onSurface     = Color(0xFFE0E0E0)
)
