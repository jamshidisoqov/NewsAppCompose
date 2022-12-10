package uz.gita.news_app_compose.data.remote.response

data class NewsData(
    val author: String?,
    val description: String?,
    val source: SourceData,
    val title: String,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)