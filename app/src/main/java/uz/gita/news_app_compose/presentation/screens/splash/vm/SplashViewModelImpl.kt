package uz.gita.news_app_compose.presentation.screens.splash.vm

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.news_app_compose.navigation.AppNavigator

import uz.gita.news_app_compose.presentation.screens.main.MainScreen
import javax.inject.Inject

@HiltViewModel
class SplashViewModelImpl @Inject constructor(
    private val navigator: AppNavigator
) : SplashViewModel, ViewModel() {
    override val container: Container<SplashUIState, Nothing> =
        container(SplashUIState("Splash Screen"))

    override fun onEventDispatcher(intent: SplashIntent) = intent {
        when (intent) {

            SplashIntent.OpenMaiScreen -> {
                delay(2000)
                navigator.navigateTo(MainScreen())
            }
        }
    }
}