package uz.gita.news_app_compose.direction.impl

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.news_app_compose.direction.SplashScreenDirection
import uz.gita.news_app_compose.presentation.screens.main.MainScreen
import javax.inject.Inject

class SplashScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
) : SplashScreenDirection {
    override suspend fun navigateToMain() {
        //navigator.replace(MainScreen())
    }
}