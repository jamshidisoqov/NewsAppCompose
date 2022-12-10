package uz.gita.news_app_compose.presentation.screens.main.vm.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.news_app_compose.domain.NewsRepository
import uz.gita.news_app_compose.presentation.screens.main.vm.MainIntent
import uz.gita.news_app_compose.presentation.screens.main.vm.MainUiState
import uz.gita.news_app_compose.presentation.screens.main.vm.MainViewModel
import uz.gita.news_app_compose.utils.ResultData
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) : MainViewModel, ViewModel() {
    override val container: Container<MainUiState, Nothing> = container(
        MainUiState(
            newsData = emptyList(), isError = null, message = null
        )
    )

    init {
        viewModelScope.launch {
        }
    }

 private fun getNewsDataByCategory(category: String) {
        viewModelScope.launch {
            repository.getAllNewsData(category, "USA").collect {

                intent {
                    reduce {
                        when (it) {
                            is ResultData.Error -> {
                                state.copy(isError = true)
                            }
                            is ResultData.Message -> {
                                state.copy(message = it.message)
                            }
                            is ResultData.Success -> {
                                state.copy(newsData = it.data)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onEventDispatcher(intent: MainIntent) = intent {
        when (intent) {
            MainIntent.Search -> {


            }

            MainIntent.SelectLanguage -> {

            }
        }

    }
}