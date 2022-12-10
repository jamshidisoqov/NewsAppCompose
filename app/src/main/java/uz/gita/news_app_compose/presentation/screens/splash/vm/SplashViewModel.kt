package uz.gita.news_app_compose.presentation.screens.splash.vm

import uz.gita.news_app_compose.utils.AppViewModel

/*CREATED BY
MATKARIMOV KHAYRULLO 
IN ()
*/
interface SplashViewModel : AppViewModel<SplashIntent, SplashUIState, Nothing> {
}

sealed class SplashIntent {
    object OpenMaiScreen: SplashIntent()
}

data class SplashUIState(
    val title: String
)