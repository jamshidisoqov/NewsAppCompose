package uz.gita.news_app_compose.data.remote.response

data class BaseNewsData(
    val articles: List<NewsData>,
    val status: String,
    val totalResults: Int
)
