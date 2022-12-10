package uz.gita.news_app_compose.presentation.screens.main.vm.impl

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.news_app_compose.presentation.screens.main.vm.MainIntent
import uz.gita.news_app_compose.presentation.screens.main.vm.MainUiState
import uz.gita.news_app_compose.presentation.screens.main.vm.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(

) : MainViewModel, ViewModel() {
    override val container: Container<MainUiState, Nothing> = container(MainUiState())

    init {

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