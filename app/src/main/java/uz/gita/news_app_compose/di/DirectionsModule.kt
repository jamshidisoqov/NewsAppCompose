package uz.gita.news_app_compose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.news_app_compose.direction.MainScreenDirection
import uz.gita.news_app_compose.direction.SplashScreenDirection
import uz.gita.news_app_compose.direction.impl.MainScreenDirectionImpl
import uz.gita.news_app_compose.direction.impl.SplashScreenDirectionImpl
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/10/2022
@Module
@InstallIn(ViewModelComponent::class)
interface DirectionsModule {

    @[Binds Singleton]
    fun bindSplashScreenDirection(impl: SplashScreenDirectionImpl): SplashScreenDirection

    @[Binds Singleton]
    fun bindMainScreenDirections(impl: MainScreenDirectionImpl): MainScreenDirection

}