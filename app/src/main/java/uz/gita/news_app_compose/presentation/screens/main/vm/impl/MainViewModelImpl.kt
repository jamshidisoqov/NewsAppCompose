package uz.gita.news_app_compose.presentation.screens.main.vm.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.news_app_compose.domain.NewsRepository
import uz.gita.news_app_compose.presentation.screens.main.vm.MainIntent
import uz.gita.news_app_compose.presentation.screens.main.vm.MainUiState
import uz.gita.news_app_compose.presentation.screens.main.vm.MainViewModel
import uz.gita.news_app_compose.presentation.screens.main.vm.MySideEffect
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val repository: NewsRepository
) : MainViewModel, ViewModel() {


    private var country = "in"

    private var category: String = "business"

    init {
        getNewsDataByCategory(category)
    }

    override val container: Container<MainUiState, MySideEffect> = container(MainUiState.Loading())

    private fun getNewsDataByCategory(category: String) = intent {
        this@MainViewModelImpl.category = category
        viewModelScope.launch {
            reduce { MainUiState.Loading(isLoading = true) }
            repository.getAllNewsData(category, country)
                .collectLatest { result ->
                    reduce { MainUiState.Loading(isLoading = false) }
                    result.onSuccess {
                        reduce { MainUiState.Success(newsData = it) }
                    }.onMessage {
                        postSideEffect(MySideEffect.Message(it))
                    }.onError {
                        postSideEffect(MySideEffect.Error(it.localizedMessage ?: "Unknown error"))
                    }
                }
        }
    }

    override fun onEventDispatcher(intent: MainIntent) = intent {
        when (intent) {
            MainIntent.Search -> {

            }
            is MainIntent.SelectLanguage -> {
                country = intent.lan
                getNewsDataByCategory(category)
            }
            is MainIntent.SelectedCategory->{
                getNewsDataByCategory(intent.category)
            }
        }
    }
}