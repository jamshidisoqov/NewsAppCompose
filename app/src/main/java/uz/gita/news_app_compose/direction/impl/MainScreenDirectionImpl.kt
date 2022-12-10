package uz.gita.news_app_compose.direction.impl

import cafe.adriel.voyager.navigator.Navigator
import uz.gita.news_app_compose.direction.MainScreenDirection
import javax.inject.Inject

class MainScreenDirectionImpl @Inject constructor(
    private val navigator: Navigator
): MainScreenDirection {
    override suspend fun navigateToMainDetail() {
        //navigator.push()
    }
}