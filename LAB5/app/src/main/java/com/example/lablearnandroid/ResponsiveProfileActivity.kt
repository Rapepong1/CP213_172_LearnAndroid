package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class ResponsiveProfileActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ResponsiveProfileScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponsiveProfileScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Responsive Profile") })
        }
    ) { padding ->
        // Use BoxWithConstraints to detect available screen width
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            val isTablet = maxWidth >= 600.dp

            if (isTablet) {
                // Layout for wide screens (Row)
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    ProfileImage(modifier = Modifier.size(200.dp))
                    Spacer(modifier = Modifier.width(32.dp))
                    ProfileInfo(modifier = Modifier.weight(1f))
                }
            } else {
                // Layout for narrow screens (Column)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Spacer(modifier = Modifier.height(32.dp))
                    ProfileImage(modifier = Modifier.size(150.dp))
                    Spacer(modifier = Modifier.height(24.dp))
                    ProfileInfo(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun ProfileImage(modifier: Modifier = Modifier) {
    // 1. Mock profile picture (gray box/circle)
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(Color.LightGray),
        contentAlignment = Alignment.Center
    ) {
        Text("IMAGE", color = Color.Gray, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ProfileInfo(modifier: Modifier = Modifier) {
    // 1. Text describing personal info
    Column(modifier = modifier) {
        Text(
            text = "John Doe",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Android Developer",
            fontSize = 18.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Experienced developer with a passion for building beautiful and functional mobile applications using Jetpack Compose and Kotlin.",
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { /* Action */ }) {
            Text("Edit Profile")
        }
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun MobilePreview() {
    MaterialTheme {
        ResponsiveProfileScreen()
    }
}

@Preview(showBackground = true, widthDp = 800)
@Composable
fun TabletPreview() {
    MaterialTheme {
        ResponsiveProfileScreen()
    }
}
