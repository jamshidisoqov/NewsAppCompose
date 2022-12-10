package uz.gita.news_app_compose.utils

import timber.log.Timber

// Created by Jamshid Isoqov on 12/1/2022


fun log(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}