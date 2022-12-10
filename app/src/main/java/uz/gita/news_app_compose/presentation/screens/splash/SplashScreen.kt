package uz.gita.news_app_compose.presentation.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.news_app_compose.R
import uz.gita.news_app_compose.presentation.screens.splash.vm.SplashIntent
import uz.gita.news_app_compose.presentation.screens.splash.vm.SplashUIState
import uz.gita.news_app_compose.presentation.screens.splash.vm.SplashViewModel
import uz.gita.news_app_compose.presentation.screens.splash.vm.SplashViewModelImpl

// Created by Jamshid Isoqov on 12/10/2022
class SplashScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: SplashViewModel = getViewModel<SplashViewModelImpl>()
        val uiState = viewModel.collectAsState().value
        SplashScreenContent(uiState, viewModel::onEventDispatcher)
    }
}

@Composable
fun SplashScreenContent(uiState: SplashUIState, onEventDispatcher: (SplashIntent) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        LaunchedEffect(key1 = true) {
            onEventDispatcher(SplashIntent.OpenMaiScreen)
        }
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            colorFilter = ColorFilter.tint(Color.Blue),
            contentDescription = "icon"
        )

    }
}