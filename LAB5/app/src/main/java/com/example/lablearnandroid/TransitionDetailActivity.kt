package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class TransitionDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        val data = intent.getStringExtra("EXTRA_DATA") ?: "ไม่มีข้อมูล"
        
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFE3F2FD) // สีฟ้าอ่อนให้เห็นความต่าง
                ) {
                    DetailScreen(data = data) {
                        finish()
                        // 3. ใช้ Custom Animation (Slide Down) เมื่อปิด
                        overridePendingTransition(R.anim.no_animation, R.anim.slide_out_down)
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        // จัดการ Animation เมื่อกดปุ่ม Back ของเครื่องด้วย
        overridePendingTransition(R.anim.no_animation, R.anim.slide_out_down)
    }
}

@Composable
fun DetailScreen(data: String, onClose: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "หน้าละเอียด (Detail Activity)", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "รับค่ามาคือ: $data", style = MaterialTheme.typography.bodyLarge)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClose) {
            Text("ปิดหน้าจอ (Slide Down)")
        }
    }
}
