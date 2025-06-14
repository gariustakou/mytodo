package org.garius.mytodo.todo.presentation.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import mytodo.composeapp.generated.resources.Res
import mytodo.composeapp.generated.resources.todo
import org.garius.mytodo.core.presentation.ui.LargeSpacing
import org.garius.mytodo.core.presentation.ui.PrimaryText
import org.garius.mytodo.core.presentation.ui.SecondaryText
import org.jetbrains.compose.resources.painterResource

@Composable
fun BottomNavigationBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = LargeSpacing),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BottomNavItem(
                //icon = Icons.Default.Home,
                isSelected = true
            )

            BottomNavItem(
                //icon = Icons.Default.List,
                isSelected = false
            )

            Spacer(modifier = Modifier.width(48.dp)) // Space for FAB

            BottomNavItem(
                //icon = Icons.Default.Schedule,
                isSelected = false
            )

            BottomNavItem(
                //icon = Icons.Default.Person,
                isSelected = false
            )
        }
    }
}

@Composable
private fun BottomNavItem(
    //icon: ImageVector,
    isSelected: Boolean
) {
    Icon(
        painter = painterResource(Res.drawable.todo),
        //painter = icon,
        contentDescription = null,
        tint = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.onBackground,

        modifier = Modifier.size(34.dp)
    )
}