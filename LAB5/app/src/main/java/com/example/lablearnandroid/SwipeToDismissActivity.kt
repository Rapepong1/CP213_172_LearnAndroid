package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay

class SwipeToDismissActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoScreen()
                }
            }
        }
    }
}

// 1. ViewModel ที่มี mutableStateListOf เก็บรายการข้อความ 5 รายการ
class TodoViewModel : ViewModel() {
    val todoList = mutableStateListOf(
        "ซื้อนม (Buy milk)",
        "โทรหาคุณหมอ (Call the doctor)",
        "ล้างรถ (Wash the car)",
        "ซ่อมหลังคา (Fix the roof)",
        "เตรียม Presentation (Prepare presentation)"
    )

    fun removeTodo(item: String) {
        todoList.remove(item)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(viewModel: TodoViewModel = viewModel()) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("To-Do List (Swipe to Delete)") })
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(
                items = viewModel.todoList,
                key = { it } // ใช้ item เป็น key เพื่อความถูกต้องในการจัดการ list
            ) { item ->
                SwipeToDeleteItem(
                    item = item,
                    onDelete = { viewModel.removeTodo(item) }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDeleteItem(
    item: String,
    onDelete: () -> Unit
) {
    var isRemoved by remember { mutableStateOf(false) }
    val state = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                isRemoved = true
                true
            } else {
                false
            }
        }
    )

    // เอฟเฟกต์การลบที่ดูนุ่มนวล
    AnimatedVisibility(
        visible = !isRemoved,
        exit = shrinkVertically(
            animationSpec = tween(durationMillis = 500),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        // 2. ใช้ SwipeToDismissBox (Material 3)
        SwipeToDismissBox(
            state = state,
            enableDismissFromStartToEnd = false, // ให้ปัดได้เฉพาะจากขวาไปซ้าย
            backgroundContent = {
                // 3. พื้นหลังสีแดงและ Icon ถังขยะเมื่อปัด
                val backgroundColor = if (state.dismissDirection == SwipeToDismissBoxValue.EndToStart) {
                    Color.Red
                } else {
                    Color.Transparent
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(backgroundColor)
                        .padding(horizontal = 24.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Icon",
                        tint = Color.White
                    )
                }
            },
            content = {
                // รายการปกติ
                Column {
                    ListItem(
                        headlineContent = { Text(item) },
                        modifier = Modifier.background(MaterialTheme.colorScheme.surface)
                    )
                    HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
                }
            }
        )
    }

    // 4. เมื่อปัดจนสุด ให้ลบข้อมูลออกจาก State ใน ViewModel
    LaunchedEffect(isRemoved) {
        if (isRemoved) {
            delay(500) // รอให้ Animation สิ้นสุดก่อนลบจริง
            onDelete()
        }
    }
}
