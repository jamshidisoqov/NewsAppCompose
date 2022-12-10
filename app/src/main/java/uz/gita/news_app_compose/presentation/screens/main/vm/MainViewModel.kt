package uz.gita.news_app_compose.presentation.screens.main.vm

import uz.gita.news_app_compose.data.remote.response.NewsData
import uz.gita.news_app_compose.utils.AppViewModel
import uz.gita.news_app_compose.utils.languageList

/*CREATED BY
MATKARIMOV KHAYRULLO 
IN ()
*/
interface MainViewModel : AppViewModel<MainIntent, MainUiState, MySideEffect> {
}

sealed class MainIntent {
    object Search : MainIntent()
    data class SelectLanguage(val lan:String) : MainIntent()
    data class SelectedCategory(val category:String):MainIntent()
}

sealed interface MainUiState {
    data class Success(
        val list: List<String> = listOf(
            "business",
            "entertainment",
            "general",
            "health",
            "science",
            "sports",
            "technology"
        ),
        val newsData: List<NewsData> = emptyList()
    ) : MainUiState

    data class Loading(val isLoading: Boolean = false):MainUiState

}

sealed interface MySideEffect {

    data class Message(val message: String) : MySideEffect

    data class Error(val error: String) : MySideEffect

    data class ChooseLanguage(val languagesList: List<String> = languageList) : MySideEffect

}