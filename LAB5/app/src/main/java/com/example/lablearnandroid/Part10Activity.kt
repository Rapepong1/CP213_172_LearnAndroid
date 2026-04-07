package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Part10Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    AppWidgetStudyScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppWidgetStudyScreen() {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Mission 10: App Widget Concept") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Concept Explanation
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Info, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Concept: App Widgets", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "App Widgets คือ 'หน้าต่างแอปจิ๋ว' ที่ฝังอยู่บนหน้า Home Screen ของ Android ช่วยให้ผู้ใช้เข้าถึงข้อมูลสำคัญได้ทันทีโดยไม่ต้องเปิดแอปหลัก\\n\\n" +
                                "องค์ประกอบสำคัญ:\\n" +
                                "1. AppWidgetProvider (BroadcastReceiver): ตัวจัดการเหตุการณ์ต่างๆ ของ Widget\\n" +
                                "2. AppWidgetProviderInfo (XML): ไฟล์กำหนดขนาดและคุณสมบัติ\\n" +
                                "3. RemoteViews (Layout): การวาดหน้าจอ Widget ซึ่งมีข้อจำกัดเรื่อง View ที่ใช้ได้ (ปัจจุบันใช้ Glance สำหรับเขียนด้วย Compose ได้แล้ว)",
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }

            Text("ตัวอย่างหน้าตา Widget (Mock UI):", fontWeight = FontWeight.Bold)

            // Mock Widget Preview
            Card(
                modifier = Modifier
                    .width(200.dp)
                    .height(100.dp)
                    .align(Alignment.CenterHorizontally),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize().background(Color(0xFF2196F3)).padding(12.dp)) {
                    Column {
                        Icon(Icons.Default.Widgets, contentDescription = null, tint = Color.White)
                        Text("My App Widget", color = Color.White, fontWeight = FontWeight.Bold)
                        Text("Update: 12:00 PM", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
