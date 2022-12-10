package uz.gita.news_app_compose.domain.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.news_app_compose.data.remote.api.NewsApi
import uz.gita.news_app_compose.data.remote.response.NewsData
import uz.gita.news_app_compose.domain.NewsRepository
import uz.gita.news_app_compose.utils.ResultData
import uz.gita.news_app_compose.utils.hasConnection
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {
    override fun getAllNewsData(
        category: String,
        country: String
    ): Flow<ResultData<List<NewsData>>> = flow {
        val response = api.getNews(country = country, category = category.lowercase())
        if (hasConnection()) {
            try {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        val body = response.body()!!
                        emit(ResultData.Success(body.articles))
                    } else {
                        emit(ResultData.Error(Throwable("Body null")))
                    }
                } else {
                    emit(ResultData.Error(Throwable(response.message())))
                }
            } catch (e: Exception) {
                emit(ResultData.Error(e))
            }
        } else {
            emit(ResultData.Message("No internet connection"))
        }
    }.flowOn(Dispatchers.IO)
}