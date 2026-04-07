package com.example.lablearnandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ContactListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ContactListScreen()
                }
            }
        }
    }
}

// 1. ViewModel จัดการ State และ Mock ข้อมูล
class ContactViewModel : ViewModel() {
    private val _contacts = MutableStateFlow<List<String>>(emptyList())
    val contacts: StateFlow<List<String>> = _contacts.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private var currentPage = 0
    private val pageSize = 15

    init {
        loadMoreContacts()
    }

    fun loadMoreContacts() {
        if (_isLoading.value) return
        
        viewModelScope.launch {
            _isLoading.value = true
            // Requirement 3: จำลองการโหลดข้อมูลเพิ่ม (หน่วงเวลา 2 วินาที)
            delay(2000) 

            val nextBatch = generateMockData(currentPage)
            if (nextBatch.isNotEmpty()) {
                _contacts.value = _contacts.value + nextBatch
                currentPage++
            }
            _isLoading.value = false
        }
    }

    // Helper ในการสร้างข้อมูล Mock A-Z
    private fun generateMockData(page: Int): List<String> {
        val allNames = listOf(
            "Alice", "Adam", "Arthur", 
            "Bob", "Ben", "Bella", 
            "Charlie", "Chloe", "Caleb",
            "David", "Daisy", "Daniel",
            "Eve", "Ethan", "Emma",
            "Frank", "Fiona", "Felix",
            "George", "Grace", "Gavin",
            "Hannah", "Harry", "Hazel",
            "Isaac", "Ivy", "Ian",
            "Jack", "Julia", "Jacob",
            "Kevin", "Kelly", "Kai",
            "Lily", "Leo", "Luna",
            "Max", "Mia", "Milo",
            "Noah", "Nora", "Nico",
            "Olivia", "Oliver", "Owen",
            "Paul", "Penny", "Peter",
            "Quinn", "Quentin", "Queen",
            "Rose", "Ryan", "Ruby",
            "Sam", "Sophia", "Simon",
            "Thomas", "Tara", "Toby",
            "Ursula", "Umar", "Unity",
            "Victor", "Vera", "Vince",
            "Wendy", "Will", "Wren",
            "Xander", "Xenia", "Xavier",
            "Yara", "Yosef", "Yuna",
            "Zane", "Zoe", "Zelda"
        )
        val start = page * pageSize
        val end = (page + 1) * pageSize
        return if (start < allNames.size) {
            allNames.subList(start, minOf(end, allNames.size))
        } else {
            emptyList()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(viewModel: ContactViewModel = viewModel()) {
    val contacts by viewModel.contacts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val listState = rememberLazyListState()

    // Requirement 2: จัดกลุ่มข้อมูลตามตัวอักษรแรกสำหรับ Sticky Header
    val grouped = contacts.groupBy { it.first().uppercaseChar() }

    // Requirement 3: Pagination Logic (Scroll ถึง Item สุดท้ายแล้วโหลดเพิ่ม)
    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = listState.layoutInfo.visibleItemsInfo.lastOrNull()
            lastVisibleItem != null && lastVisibleItem.index >= listState.layoutInfo.totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value && !isLoading && contacts.isNotEmpty()) {
            viewModel.loadMoreContacts()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Contact List") })
        }
    ) { padding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            grouped.forEach { (initial, names) ->
                // Requirement 2: Sticky Header แสดงตัวอักษรนำหน้า
                stickyHeader {
                    HeaderItem(initial.toString())
                }

                items(names) { name ->
                    ContactItem(name)
                }
            }

            // Requirement 4: แสดง Loading Indicator ที่ท้าย List ระหว่างโหลด
            if (isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeaderItem(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(horizontal = 16.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimaryContainer
        )
    }
}

@Composable
fun ContactItem(name: String) {
    Column {
        ListItem(
            headlineContent = { Text(name, fontWeight = FontWeight.Medium) },
            supportingContent = { Text("Mobile: 08x-xxx-xxxx", color = Color.Gray) },
            leadingContent = {
                Surface(
                    shape = androidx.compose.foundation.shape.CircleShape,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = name.first().toString(),
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        )
        HorizontalDivider(modifier = Modifier.padding(start = 72.dp), thickness = 0.5.dp, color = Color.LightGray)
    }
}

@Preview(showBackground = true)
@Composable
fun ContactListPreview() {
    MaterialTheme {
        ContactListScreen()
    }
}
