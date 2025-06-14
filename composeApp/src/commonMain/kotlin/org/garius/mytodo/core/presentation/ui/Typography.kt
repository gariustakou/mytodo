package org.garius.mytodo.core.presentation.ui

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import mytodo.composeapp.generated.resources.Res
import mytodo.composeapp.generated.resources.poppins_bold
import mytodo.composeapp.generated.resources.poppins_medium
import mytodo.composeapp.generated.resources.poppins_regular
import mytodo.composeapp.generated.resources.poppins_semibold
import org.jetbrains.compose.resources.Font

    // Déclaration de la famille Poppins
    @Composable
    fun AppTypography(): Typography {
        // Déclaration de la famille Poppins
        val poppins = FontFamily(
            Font(Res.font.poppins_regular, FontWeight.Normal),
            Font(Res.font.poppins_medium, FontWeight.Medium),
            Font(Res.font.poppins_semibold, FontWeight.SemiBold),
            Font(Res.font.poppins_bold, FontWeight.Bold)
        )

        // Using Material 3 Typography. Adjust parameters as needed.
        return Typography(
            // Titres principaux (ex: date, titres de section)
            headlineLarge = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                // You might want to add letterSpacing or lineHeight for headline styles
                // letterSpacing = 0.sp,
                // lineHeight = 28.sp
            ),
            // Texte courant (ex: descriptions, libellés)
            bodyLarge = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                // letterSpacing = 0.5.sp,
                // lineHeight = 24.sp
            ),
            // Texte secondaire (ex: salutations)
            bodyMedium = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                // letterSpacing = 0.25.sp,
                // lineHeight = 20.sp
            ),
            // Petits labels (ex: heures, badges)
            labelSmall = TextStyle(
                fontFamily = poppins,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
                // letterSpacing = 0.5.sp,
                // lineHeight = 16.sp
            )

        )
    }
