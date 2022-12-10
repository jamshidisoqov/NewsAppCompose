package uz.gita.news_app_compose.navigation

import cafe.adriel.voyager.androidx.AndroidScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

// Created by Jamshid Isoqov an 9/21/2022
@Singleton
class NavigationDispatcher @Inject constructor() : NavigationHandler, AppNavigation {
    override val navStack = MutableSharedFlow<NavigationArgs>()


    private suspend fun navigate(arg: NavigationArgs) {
        navStack.emit(arg)
    }

    override suspend fun back() = navigate {
        pop()
    }

    override suspend fun backAll() = navigate {
        popAll()
    }

    override suspend fun backToRoot() = navigate {
        popUntilRoot()
    }

    override suspend fun navigateTo(screen: AndroidScreen) = navigate {
        push(screen)
    }

}