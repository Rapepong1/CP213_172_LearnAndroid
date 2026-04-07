package com.example.lablearnandroid

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class WebViewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    WebViewScreen()
                }
            }
        }
    }
}

// 1. ViewModel เก็บสถานะ URL
class WebViewModel : ViewModel() {
    var url by mutableStateOf("https://www.google.com")
        private set

    fun updateUrl(newUrl: String) {
        // เพิ่ม https:// ถ้าไม่มี
        val formattedUrl = if (!newUrl.startsWith("http")) "https://$newUrl" else newUrl
        url = formattedUrl
    }
}

@SuppressLint("SetJavaScriptEnabled")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewScreen(viewModel: WebViewModel = viewModel()) {
    var textFieldValue by remember { mutableStateOf(viewModel.url) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 8.dp)
                    ) {
                        // 4. TextField สำหรับพิมพ์ URL
                        TextField(
                            value = textFieldValue,
                            onValueChange = { textFieldValue = it },
                            modifier = Modifier.weight(1f),
                            singleLine = true,
                            placeholder = { Text("Enter URL") }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // ปุ่ม Go เพื่ออัปเดต URL
                        Button(onClick = { viewModel.updateUrl(textFieldValue) }) {
                            Text("Go")
                        }
                    }
                }
            )
        }
    ) { padding ->
        // 2. ใช้ AndroidView เพื่อฝัง WebView
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    // 3. ตั้งค่า WebViewClient
                    webViewClient = WebViewClient()
                    settings.javaScriptEnabled = true
                }
            },
            update = { webView ->
                // 6. จัดการ update block เมื่อค่า URL ใน ViewModel เปลี่ยน
                if (webView.url != viewModel.url) {
                    webView.loadUrl(viewModel.url)
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        )
    }
}
