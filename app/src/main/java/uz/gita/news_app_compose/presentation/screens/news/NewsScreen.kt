package uz.gita.news_app_compose.presentation.screens.news

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import cafe.adriel.voyager.androidx.AndroidScreen

// Created by Jamshid Isoqov on 12/10/2022
class NewsScreen : AndroidScreen() {
    @Composable
    override fun Content() {

    }
}

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsScreenContent(){
    
    AndroidView(factory = {context ->
        WebView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {

                }
            }
            settings.javaScriptEnabled = true

            loadUrl(url)
        }
    })
    
}