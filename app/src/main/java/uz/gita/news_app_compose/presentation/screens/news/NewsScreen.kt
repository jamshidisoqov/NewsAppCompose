package uz.gita.news_app_compose.presentation.screens.news

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import cafe.adriel.voyager.androidx.AndroidScreen
import uz.gita.news_app_compose.data.remote.response.NewsData

// Created by Jamshid Isoqov on 12/10/2022
class NewsScreen(private val newsData: NewsData) : AndroidScreen() {
    @Composable
    override fun Content() {
        NewsScreenContent(newsData)
    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsScreenContent(newsData: NewsData) {

    var progressState: Boolean by remember { mutableStateOf(true) }


    CustomProgressBar(progress = progressState, modifier = Modifier.fillMaxSize())


    AndroidView(factory = { context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
                    progressState = false
                }
            }
            webChromeClient = WebChromeClient()
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadsImagesAutomatically = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            loadUrl(newsData.url)
        }
    }, modifier = Modifier.fillMaxSize())

}

@Preview(showSystemUi = true)
@Composable
fun NewsScreenPreview(){
    //NewsScreenContent()
}