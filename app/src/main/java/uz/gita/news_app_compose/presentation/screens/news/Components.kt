package uz.gita.news_app_compose.presentation.screens.news

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import uz.gita.news_app_compose.data.remote.response.NewsData
import uz.gita.news_app_compose.utils.HORIZONTAL_MARGIN_STD
import uz.gita.news_app_compose.utils.ROUNDED_CORNER
import uz.gita.news_app_compose.utils.VERTICAL_MARGIN_STD
import uz.gita.news_app_compose.utils.languageList

// Created by Jamshid Isoqov on 12/10/2022


@Composable
fun CustomProgressBar(
    progress: Boolean,
    modifier: Modifier
) {
    if (progress) {
        Box(modifier = modifier) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}


@Composable
fun CustomAppBottomButton(
    text: String,
    modifier: Modifier,
    isEnabled: Boolean,
    click: () -> Unit
) {
    Button(
        onClick = click::invoke,
        modifier = modifier,
        enabled = isEnabled,
        shape = RoundedCornerShape(size = ROUNDED_CORNER)
    ) {
        Text(text = text, color = if (isEnabled) Color.White else Color.Black)
    }
}

@Preview
@Composable
fun CustomAppBottomButtonPreview() {
    CustomAppBottomButton(
        text = "Ok",
        modifier = Modifier
            .padding(horizontal = HORIZONTAL_MARGIN_STD, vertical = VERTICAL_MARGIN_STD)
            .fillMaxWidth(),
        isEnabled = false
    ) {

    }
}


@Composable
fun ErrorDialog(error: String, onDismiss: () -> Unit) {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(200.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {

                    Text(
                        text = "Error",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )

                    Spacer(modifier = Modifier.height(HORIZONTAL_MARGIN_STD))

                    Text(text = error, fontSize = 16.sp)

                    Spacer(modifier = Modifier.weight(1f))

                    CustomAppBottomButton(
                        text = "Close",
                        modifier = Modifier.fillMaxWidth(),
                        isEnabled = true
                    ) {
                        showDialog = false
                        onDismiss.invoke()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorDialogPreview() {
    ErrorDialog(error = "No internet connection") {}
}

@Preview(showBackground = true)
@Composable
fun MessageDialogPreview() {
    MessageDialog(message = "No internet connection") {}
}

@Composable
fun MessageDialog(message: String, onDismiss: () -> Unit) {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(200.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {

                    Text(
                        text = "Message",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(HORIZONTAL_MARGIN_STD))

                    Text(text = message, fontSize = 16.sp)

                    Spacer(modifier = Modifier.weight(1f))

                    CustomAppBottomButton(
                        text = "Close",
                        modifier = Modifier.fillMaxWidth(),
                        isEnabled = true
                    ) {
                        showDialog = false
                        onDismiss.invoke()
                    }
                }
            }
        }
    }
}

@Composable
fun DeleteDialog(
    message: String = "Are you really delete book?",
    onDelete: (Boolean) -> Unit
) {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        Dialog(onDismissRequest = { }) {
            Box(
                modifier = Modifier
                    .width(320.dp)
                    .height(200.dp)
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                ) {

                    Text(
                        text = "Delete",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )

                    Spacer(modifier = Modifier.height(HORIZONTAL_MARGIN_STD))

                    Text(text = message, fontSize = 16.sp)

                    Spacer(modifier = Modifier.weight(1f))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        TextButton(onClick = {
                            showDialog = false
                            onDelete.invoke(true)
                        }, modifier = Modifier.weight(1f)) {
                            Text(text = "Delete")
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        CustomAppBottomButton(
                            text = "Cancel",
                            modifier = Modifier.weight(1f),
                            isEnabled = true
                        ) {
                            onDelete.invoke(false)
                            showDialog = false
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DeleteDialogPreview() {
    DeleteDialog("Are you really delete book?") {

    }
}

@Composable
fun ChooseLanguageDialog(languageList: List<String>, onClick: (String) -> Unit) {
    Dialog(onDismissRequest = { }) {
        Column(
            modifier = Modifier
                .width(320.dp)
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(24.dp)
        ) {

            Text(
                text = "Choose language",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            LazyColumn(
                modifier = Modifier
                    .padding(top = HORIZONTAL_MARGIN_STD)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(languageList.size) {
                    val data = languageList[it]
                    Text(
                        text = data.uppercase(),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                            .clickable {
                                onClick.invoke(data)
                            }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ChooseLanguageDialogPreview() {
    ChooseLanguageDialog(languageList = languageList) {

    }
}

@Composable
fun NewsItem(newsData: NewsData, onClick: (NewsData) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick.invoke(newsData)
            }
            .clip(shape = RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = newsData.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(text = newsData.author ?: "", fontSize = 16.sp)
                }
                Column(
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(20.dp))
                        .background(MaterialTheme.colorScheme.inversePrimary)
                        .padding(4.dp)
                ) {
                    Text(
                        text = newsData.source.name,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = newsData.publishedAt, fontSize = 12.sp)
                }
            }
            Text(text = newsData.description ?: "", fontSize = 12.sp)
        }
    }
}