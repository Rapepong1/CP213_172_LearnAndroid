package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class Part1AnimationActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LikeButtonScreen()
                }
            }
        }
    }
}

@Composable
fun LikeButtonScreen() {
    // 1. ใช้ remember เก็บสถานะ Liked
    var isLiked by remember { mutableStateOf(false) }
    
    // ใช้ trigger สำหรับ Scale animation (ขยายแล้วกลับมาขนาดเดิม)
    var isAnimateTrigger by remember { mutableStateOf(false) }

    // Requirement 1: Scale animation โดยใช้ animateFloatAsState และ spring
    val scale by animateFloatAsState(
        targetValue = if (isAnimateTrigger) 1.2f else 1.0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "ScaleAnimation",
        finishedListener = {
            // เมื่อขยายเสร็จแล้วให้ reset ค่ากลับเพื่อให้มันย่อกลับมาขนาดเดิม
            if (isAnimateTrigger) isAnimateTrigger = false
        }
    )

    // Requirement 2: เปลี่ยนสีพื้นหลังปุ่มจากเทาเป็นชมพู โดยใช้ animateColorAsState
    val backgroundColor by animateColorAsState(
        targetValue = if (isLiked) Color(0xFFF06292) else Color.LightGray,
        label = "ColorAnimation"
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                isLiked = !isLiked
                isAnimateTrigger = true // เริ่ม Animation ขยาย
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColor,
                contentColor = Color.White
            ),
            modifier = Modifier.scale(scale)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = if (isLiked) "Liked" else "Like")

                // Requirement 3: ใช้ AnimatedVisibility แสดง Icon รูปหัวใจเมื่อ Liked
                AnimatedVisibility(visible = isLiked) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Heart Icon",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LikeButtonPreview() {
    MaterialTheme {
        LikeButtonScreen()
    }
}
