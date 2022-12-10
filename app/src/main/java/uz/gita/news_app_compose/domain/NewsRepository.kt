package uz.gita.news_app_compose.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.news_app_compose.data.remote.response.NewsData
import uz.gita.news_app_compose.utils.ResultData

// Created by Jamshid Isoqov on 12/10/2022
interface NewsRepository {

    fun getAllNewsData():Flow<ResultData<List<NewsData>>>

}