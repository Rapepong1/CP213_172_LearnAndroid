package com.example.lablearnandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class TransitionMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen {
                        val intent = Intent(this, TransitionDetailActivity::class.java).apply {
                            putExtra("EXTRA_DATA", "ข้อมูลจากหน้าแรก")
                        }
                        startActivity(intent)
                        // 2. ใช้ Custom Animation (Slide Up)
                        overridePendingTransition(R.anim.slide_in_up, R.anim.no_animation)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(onOpenDetail: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "หน้าหลัก (Main Activity)", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onOpenDetail) {
            Text("เปิดหน้า Detail (Slide Up)")
        }
    }
}
