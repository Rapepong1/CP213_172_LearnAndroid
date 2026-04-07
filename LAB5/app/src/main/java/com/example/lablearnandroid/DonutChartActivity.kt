package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class DonutChartActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DonutChartScreen()
                }
            }
        }
    }
}

@Composable
fun DonutChartScreen() {
    // 1. กำหนดข้อมูลสัดส่วนและสี
    val proportions = listOf(30f, 40f, 30f)
    val colors = listOf(
        Color(0xFF64B5F6), // Blue
        Color(0xFF81C784), // Green
        Color(0xFFFFB74D)  // Orange
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Expense Summary",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // เรียกใช้งาน DonutChart
        DonutChart(
            proportions = proportions,
            colors = colors,
            modifier = Modifier.size(250.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Legend แสดงคำอธิบายสี
        Legend(proportions, colors)
    }
}

@Composable
fun DonutChart(
    proportions: List<Float>,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    // 3. Animation: ค่อยๆ วาดจาก 0 ถึง 360 องศา
    val animationProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animationProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1500)
        )
    }

    // 2. ใช้ Canvas วาด DrawArc
    Canvas(modifier = modifier) {
        val strokeWidth = 50.dp.toPx()
        var startAngle = -90f // เริ่มจากด้านบนสุด

        proportions.forEachIndexed { index, proportion ->
            // คำนวณมุมที่ต้องกวาดไป (Sweep Angle) ตามสัดส่วน
            val sweepAngle = (proportion / 100f) * 360f
            
            // วาดส่วนโค้งโดยคูณกับ animationProgress เพื่อทำ Animation
            drawArc(
                color = colors[index % colors.size],
                startAngle = startAngle,
                sweepAngle = sweepAngle * animationProgress.value,
                useCenter = false,
                style = Stroke(width = strokeWidth, cap = StrokeCap.Butt)
            )
            
            startAngle += sweepAngle
        }
    }
}

@Composable
fun Legend(proportions: List<Float>, colors: List<Color>) {
    val labels = listOf("Food", "Rent", "Travel")
    Column {
        proportions.forEachIndexed { index, value ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 4.dp)
            ) {
                Surface(
                    color = colors[index % colors.size],
                    modifier = Modifier.size(16.dp),
                    shape = MaterialTheme.shapes.small
                ) {}
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "${labels[index]}: ${value.toInt()}%", fontSize = 16.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DonutChartPreview() {
    MaterialTheme {
        DonutChartScreen()
    }
}
