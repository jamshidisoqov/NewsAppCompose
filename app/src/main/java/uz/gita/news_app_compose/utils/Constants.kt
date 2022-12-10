package uz.gita.news_app_compose.utils

import androidx.compose.ui.unit.dp
import timber.log.Timber

// Created by Jamshid Isoqov on 12/1/2022


fun log(message: String, tag: String = "TTT") {
    Timber.tag(tag).d(message)
}

val ROUNDED_CORNER = 12.dp
val HORIZONTAL_MARGIN_STD = 12.dp
val VERTICAL_MARGIN_STD = 12.dp