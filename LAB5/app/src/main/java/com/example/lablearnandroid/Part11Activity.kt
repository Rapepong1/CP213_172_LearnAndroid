package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

class Part11Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    SkeletonLoadingScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkeletonLoadingScreen() {
    var isLoading by remember { mutableStateOf(true) }

    // Simulate data loading
    LaunchedEffect(Unit) {
        delay(4000)
        isLoading = false
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mission 11: Skeleton Loading") }) }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Card(colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Concept: Skeleton Loading", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Skeleton Loading คือการแสดง 'โครงร่าง' ของเนื้อหาก่อนที่ข้อมูลจริงจะโหลดเสร็จ แทนที่จะใช้วงกลมหมุน (ProgressBar) อย่างเดียว " +
                                    "การใช้ Skeleton ช่วยให้ผู้ใช้รู้สึกว่าแอปทำงานเร็วขึ้นเพราะเห็นโครงสร้างของหน้าจอทันที\\n\\n" +
                                    "เทคนิคที่นิยม: ใช้ Shimmer Animation (เงาวิ่งผ่าน) เพื่อบอกสถานะว่ากำลังโหลดอยู่",
                            fontSize = 14.sp
                        )
                    }
                }
            }

            if (isLoading) {
                items(5) { SkeletonItem() }
            } else {
                items(5) { ActualItem(it) }
            }
        }
    }
}

@Composable
fun SkeletonItem() {
    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerTranslate"
    )

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f),
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim, y = translateAnim)
    )

    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Box(modifier = Modifier.size(60.dp).clip(CircleShape).background(brush))
        Spacer(modifier = Modifier.width(16.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Box(modifier = Modifier.fillMaxWidth(0.7f).height(20.dp).clip(RoundedCornerShape(4.dp)).background(brush))
            Spacer(modifier = Modifier.height(8.dp))
            Box(modifier = Modifier.fillMaxWidth(0.5f).height(16.dp).clip(RoundedCornerShape(4.dp)).background(brush))
        }
    }
}

@Composable
fun ActualItem(index: Int) {
    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Surface(modifier = Modifier.size(60.dp), shape = CircleShape, color = MaterialTheme.colorScheme.primary) {
            Box(contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("${index + 1}", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text("User Name ${index + 1}", fontWeight = FontWeight.Bold)
            Text("Last active: 2 hours ago", color = Color.Gray, fontSize = 12.sp)
        }
    }
}
