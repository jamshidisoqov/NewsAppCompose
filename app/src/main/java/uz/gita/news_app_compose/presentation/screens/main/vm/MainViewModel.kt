package uz.gita.news_app_compose.presentation.screens.main.vm

import uz.gita.news_app_compose.data.remote.response.Source
import uz.gita.news_app_compose.utils.AppViewModel

/*CREATED BY
MATKARIMOV KHAYRULLO 
IN ()
*/
interface MainViewModel : AppViewModel<MainIntent, MainUiState, Nothing> {
}

sealed class MainIntent {
    object Search : MainIntent()
    object SelectLanguage : MainIntent()
}

data class MainUiState(
    val list: List<String> = listOf(
        "business",
        "entertainment",
        "general",
        "health",
        "science",
        "sports",
        "technology"

    ),
)
