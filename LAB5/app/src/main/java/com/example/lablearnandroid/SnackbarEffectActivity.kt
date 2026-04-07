package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SnackbarEffectActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SnackbarEffectScreen()
                }
            }
        }
    }
}

// 1. ViewModel ที่ใช้ SharedFlow สำหรับส่ง One-time event (Error message)
class SnackbarViewModel : ViewModel() {
    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow = _errorFlow.asSharedFlow()

    fun triggerError() {
        viewModelScope.launch {
            // จำลองการเกิด Error
            _errorFlow.emit("เกิดข้อผิดพลาดในการโหลดข้อมูล! (Error 404)")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackbarEffectScreen(viewModel: SnackbarViewModel = viewModel()) {
    // 2. เตรียม SnackbarHostState
    val snackbarHostState = remember { SnackbarHostState() }

    // 3. ใช้ LaunchedEffect เพื่อ Observe SharedFlow จาก ViewModel
    // เมื่อมี Error ส่งมา จะสั่งแสดง Snackbar ทันที (Side Effect)
    LaunchedEffect(Unit) {
        viewModel.errorFlow.collect { message ->
            snackbarHostState.showSnackbar(
                message = message,
                actionLabel = "ตกลง",
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Snackbar Side Effect") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "คลิกปุ่มเพื่อจำลอง Error และแสดง Snackbar",
                modifier = Modifier.padding(16.dp)
            )

            // 4. ปุ่ม Trigger Error สำหรับทดสอบ
            Button(onClick = { viewModel.triggerError() }) {
                Text("Trigger Error")
            }
        }
    }
}
