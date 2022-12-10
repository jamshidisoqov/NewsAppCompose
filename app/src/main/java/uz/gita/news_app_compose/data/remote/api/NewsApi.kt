package uz.gita.news_app_compose.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.news_app_compose.data.remote.response.BaseNewsData

// Created by Jamshid Isoqov on 12/10/2022
interface NewsApi {

    @GET("/v2/top-headlines")
    suspend fun getNews(
        @Query("country") country: String,
        @Query("category") category: String?,
        @Query("apiKey") key: String = "2c57cf5b95034a67a9408c2d07919d3b"
    ): Response<BaseNewsData>

}