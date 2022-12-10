package uz.gita.news_app_compose.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.news_app_compose.R
import uz.gita.news_app_compose.presentation.screens.main.vm.MainIntent
import uz.gita.news_app_compose.presentation.screens.main.vm.MainUiState
import uz.gita.news_app_compose.presentation.screens.main.vm.MainViewModel
import uz.gita.news_app_compose.presentation.screens.main.vm.impl.MainViewModelImpl
import uz.gita.news_app_compose.presentation.screens.news.CustomProgressBar

class MainScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: MainViewModel = getViewModel<MainViewModelImpl>()
        val uiState = viewModel.collectAsState().value
        MainScreenContent(uiState, viewModel::onEventDispatcher)
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
                    androidx.compose.material3.IconButton(onClick = {
                        onEventDispatcher(MainIntent.Search)

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

                HorizontalPager(
                    count = uiState.list.size,
                    state = pageState
                ) { index ->
                    Text(text = uiState.list[index])
                }
            }
        }
    }
}
