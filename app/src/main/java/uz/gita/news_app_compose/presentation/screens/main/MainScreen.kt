package uz.gita.news_app_compose.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.IconButton
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.news_app_compose.R
import uz.gita.news_app_compose.presentation.screens.main.vm.MainIntent
import uz.gita.news_app_compose.presentation.screens.main.vm.MainUiState
import uz.gita.news_app_compose.presentation.screens.main.vm.MainViewModel
import uz.gita.news_app_compose.presentation.screens.main.vm.MySideEffect
import uz.gita.news_app_compose.presentation.screens.main.vm.impl.MainViewModelImpl
import uz.gita.news_app_compose.presentation.screens.news.*
import uz.gita.news_app_compose.utils.languageList

class MainScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: MainViewModel = getViewModel<MainViewModelImpl>()
        val uiState = viewModel.collectAsState().value
        MainScreenContent(uiState, viewModel::onEventDispatcher)

        var openLanguage: Boolean by remember {
            mutableStateOf(false)
        }
        var errorState: String by remember { mutableStateOf("") }

        var messageState: String by remember { mutableStateOf("") }

        viewModel.collectSideEffect {
            when (it) {
                is MySideEffect.Error -> {
                    errorState = it.error
                }
                is MySideEffect.Message -> {
                    messageState = it.message
                }
                is MySideEffect.ChooseLanguage -> {
                    openLanguage = true
                }
            }
        }

        if (errorState.isNotEmpty()) {
            ErrorDialog(error = errorState) {
                errorState = ""
            }
        }

        if (messageState.isNotEmpty()) {
            MessageDialog(message = errorState) {
                messageState = ""
            }
        }

        if (openLanguage) {
            ChooseLanguageDialog(languageList = languageList) {
                viewModel.onEventDispatcher(MainIntent.SelectLanguage(it))
                openLanguage = false
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreenContent(uiState: MainUiState, onEventDispatcher: (MainIntent) -> Unit) {

    val pageState = rememberPagerState()
    val currentPage = pageState.currentPage
    val scope = rememberCoroutineScope()

    when (uiState) {
        is MainUiState.Loading -> {
            CustomProgressBar(progress = uiState.isLoading, modifier = Modifier.fillMaxSize())
        }
        is MainUiState.Success -> {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary),
                    horizontalArrangement = Arrangement.End,
                ) {
                    IconButton(onClick = {
                        onEventDispatcher(MainIntent.Search)
                    }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "")

                    }
                    IconButton(onClick = {
                        onEventDispatcher.invoke(MainIntent.OpenLanguage)
                    }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "")
                    }
                }
                TabRow(
                    selectedTabIndex = currentPage,
                    backgroundColor = MaterialTheme.colorScheme.primary
                ) {
                    uiState.list.forEachIndexed { index, _ ->
                        Tab(selected = currentPage == index, onClick = {
                            scope.launch {
                                pageState.animateScrollToPage(index)
                                onEventDispatcher.invoke(MainIntent.SelectedCategory(uiState.list[index]))
                            }
                        }, modifier = Modifier.padding(8.dp)) {
                            when (uiState.list[index]) {
                                "business" -> {
                                    Icon(
                                        painter = painterResource(id = R.drawable.business),
                                        contentDescription = ""
                                    )
                                }
                                "entertainment" -> {
                                    Icon(
                                        painter = painterResource(id = R.drawable.entertainment),
                                        contentDescription = ""
                                    )
                                }
                                "general" -> {
                                    Icon(
                                        painter = painterResource(id = R.drawable.general),
                                        contentDescription = ""
                                    )
                                }
                                "health" -> {
                                    Icon(
                                        painter = painterResource(id = R.drawable.health),
                                        contentDescription = ""
                                    )
                                }
                                "science" -> {
                                    Icon(
                                        painter = painterResource(id = R.drawable.science),
                                        contentDescription = ""
                                    )
                                }
                                "sports" -> {
                                    Icon(
                                        painter = painterResource(id = R.drawable.sports),
                                        contentDescription = ""
                                    )
                                }
                                "technology" -> {
                                    Icon(
                                        painter = painterResource(id = R.drawable.tech),
                                        contentDescription = ""
                                    )
                                }
                            }

                        }
                    }
                }

                val navigation = LocalNavigator.currentOrThrow

                HorizontalPager(
                    count = uiState.list.size,
                    state = pageState
                ) {
                    LazyColumn(modifier = Modifier.fillMaxWidth()){
                       items(uiState.list.size){
                           NewsItem(newsData = uiState.newsData[it]){data->
                               navigation.push(NewsScreen(data))
                           }
                       }
                   }
                }
            }
        }
    }
}
