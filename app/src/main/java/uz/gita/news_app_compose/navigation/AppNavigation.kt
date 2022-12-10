package uz.gita.news_app_compose.navigation

import cafe.adriel.voyager.androidx.AndroidScreen

// Created by Jamshid Isoqov an 9/21/2022
typealias AppScreen = AndroidScreen

interface AppNavigation {

    suspend fun back()

    suspend fun backAll()

    suspend fun backToRoot()

    suspend fun navigateTo(screen: AndroidScreen)

}