package uz.gita.news_app_compose.di
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.news_app_compose.navigation.AppNavigator
import uz.gita.news_app_compose.navigation.NavigationHandler
import uz.gita.news_app_compose.navigation.NavigatorDispatcher


@Module
@InstallIn(SingletonComponent::class)
interface NavigatorModule {

    @Binds
    fun appNavigator(dispatcher: NavigatorDispatcher): AppNavigator

    @Binds
    fun navigationHandler(dispatcher: NavigatorDispatcher): NavigationHandler
}