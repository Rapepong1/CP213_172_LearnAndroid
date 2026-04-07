package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CollapsingLayoutActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
                
                Scaffold(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        LargeTopAppBar(
                            title = {
                                Text(
                                    "Collapsing Layout",
                                    maxLines = 1
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                                }
                            },
                            scrollBehavior = scrollBehavior
                        )
                    }
                ) { innerPadding ->
                    CollapsingContent(innerPadding)
                }
            }
        }
    }
}

@Composable
fun CollapsingContent(padding: PaddingValues) {
    LazyColumn(
        contentPadding = padding,
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Concept: Collapsing Toolbar",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Collapsing Toolbar ใน Jetpack Compose ใช้หลักการของ Nested Scroll โดยการผูก TopAppBar เข้ากับ ScrollBehavior และส่งผ่านสถานะการ Scroll จาก LazyColumn ไปยัง TopAppBar ทำให้เมื่อเราเลื่อนหน้าจอขึ้น ส่วนหัวจะค่อยๆ หดเล็กลง (Collapse) หรือขยายใหญ่ขึ้น (Expand) ตามการลากนิ้วของผู้ใช้ ช่วยเพิ่มพื้นที่ในการแสดงผลเนื้อหาหลักได้มากขึ้น",
                        lineHeight = 24.sp
                    )
                }
            }
        }
        
        items((1..30).toList()) { index ->
            ListItem(
                headlineContent = { Text("รายการที่ $index") },
                supportingContent = { Text("รายละเอียดของไอเทมลำดับที่ $index") }
            )
            HorizontalDivider()
        }
    }
}
