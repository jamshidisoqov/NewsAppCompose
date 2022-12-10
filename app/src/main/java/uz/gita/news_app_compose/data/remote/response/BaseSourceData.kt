package uz.gita.news_app_compose.data.remote.response


data class BaseSourceData(
    val status: String,
    val sources: List<Source>
)
