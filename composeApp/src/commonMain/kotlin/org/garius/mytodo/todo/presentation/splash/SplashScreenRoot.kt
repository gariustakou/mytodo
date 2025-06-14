package org.garius.mytodo.todo.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import mytodo.composeapp.generated.resources.Res
import mytodo.composeapp.generated.resources.logo
import mytodo.composeapp.generated.resources.mytodo
import org.garius.mytodo.core.presentation.ObserveAsEvents
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SplashScreenRoot(
    onNavigateToHome: () -> Unit,
    viewModel: SplashViewModel = koinViewModel(),
) {

    ObserveAsEvents(viewModel.events) { event ->
        when(event) {
            is SplashEvent.NavigateToHome -> {
                   onNavigateToHome()
            }
        }
    }
    SplashScreen(
        state = viewModel.state,
        onAction = { action ->
//            when (action) {
//                //SplashAction.Initialize ->
//
//            }
            viewModel.onAction(action)
        }
    )
}

@Composable
@Preview
private fun SplashScreen(
    state: SplashState,
    onAction: (SplashAction) -> Unit,
){

    // Lancer l'initialisation au premier rendu
    LaunchedEffect(Unit) {
        delay(3000)
        onAction(SplashAction.Initialize)
    }

    Scaffold {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(Res.drawable.mytodo),
                contentDescription = stringResource(Res.string.logo),
                modifier = Modifier.size(600.dp),
            )
        }
    }
}
