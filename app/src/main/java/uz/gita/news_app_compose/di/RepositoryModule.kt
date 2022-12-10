package uz.gita.news_app_compose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.news_app_compose.domain.NewsRepository
import uz.gita.news_app_compose.domain.impl.NewsRepositoryImpl
import javax.inject.Singleton

// Created by Jamshid Isoqov on 12/10/2022
@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @[Binds Singleton]
    fun bindRepository(impl: NewsRepositoryImpl): NewsRepository
}