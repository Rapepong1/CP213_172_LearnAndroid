package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Part12Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    DialogAndSheetScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogAndSheetScreen() {
    var showSheet by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mission 12: Dialog & Bottom Sheet") }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Concept Explanation
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Concept: Dialog & Bottom Sheet", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "1. Modal Bottom Sheet: เป็นหน้าต่างที่เลื่อนขึ้นมาจากด้านล่าง เหมาะสำหรับการแสดงตัวเลือกเสริม หรือเมนูเพิ่มเติม โดยไม่ทำให้ผู้ใช้หลุดจากบริบทเดิมมากนัก\\n\\n" +
                                "2. AlertDialog (Middle Dialog): เป็นหน้าต่างที่เด้งขึ้นมาตรงกลางหน้าจอ เพื่อขัดจังหวะผู้ใช้เมื่อต้องการการยืนยันที่สำคัญ หรือแจ้งเตือนข้อมูลที่ต้องได้รับความสนใจทันที",
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { showSheet = true }, modifier = Modifier.fillMaxWidth()) {
                Text("Show Modal Bottom Sheet")
            }

            Button(onClick = { showDialog = true }, modifier = Modifier.fillMaxWidth()) {
                Text("Show Middle Dialog (AlertDialog)")
            }

            // Bottom Sheet Implementation
            if (showSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showSheet = false },
                    sheetState = sheetState
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 64.dp, start = 16.dp, end = 16.dp, top = 16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("นี่คือ Modal Bottom Sheet", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text("คุณสามารถใส่ Composable อะไรก็ได้ลงในนี้")
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { showSheet = false }) {
                            Text("ปิด Sheet")
                        }
                    }
                }
            }

            // AlertDialog Implementation
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("การยืนยัน") },
                    text = { Text("คุณต้องการเรียนรู้เรื่อง Dialog ต่อไปใช่หรือไม่?") },
                    confirmButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("ใช่")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { showDialog = false }) {
                            Text("ยกเลิก")
                        }
                    }
                )
            }
        }
    }
}
