package com.example.myapplication

import android.app.ListActivity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape // ต้อง import เพิ่ม
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults // ต้อง import เพิ่ม
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip // ต้อง import เพิ่ม
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab.LitActivity


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // กำหนดสีปุ่มที่อยากได้เก็บไว้ในตัวแปร (จะได้แก้ที่เดียว)
            val myButtonColor = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1B5E20), // สีพื้นหลังปุ่ม (เขียวเข้ม)
                contentColor = Color.White          // สีไอคอน/ตัวหนังสือ
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0xFF2E7D32), Color(0xFF66BB6A))
                        )
                    )
                    .padding(32.dp)){
                //hp
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(32.dp)
                    .background(color = Color.White)) {
                    Text(
                        text = "HP",
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .fillMaxWidth(fraction = 0.72f)
                            .background(color = Color.Red)
                            .padding(8.dp)
                    )
                }


                //image
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "Profile",
                    modifier = Modifier
                        .size(200.dp)
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 16.dp)
                        .clip(RoundedCornerShape(30.dp)) // <--- แก้ไข 1: ตัดขอบมน (เปลี่ยนเลข dp เพื่อเพิ่มความมน)
                        .clickable {
                            startActivity(Intent(this@MainActivity, LitActivity::class.java))
                        }
                )

                var str by remember { mutableIntStateOf(8) }
                var agi by remember { mutableIntStateOf(10) }
                var int by remember { mutableIntStateOf(15) }

                //status
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // --- STR Column ---
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Button(
                            onClick = { str = str + 1 },
                            colors = myButtonColor // <--- แก้ไข 2: ใส่สีปุ่ม
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_arrow_drop_up_24),
                                contentDescription = "up"
                            )
                        }

                        Text(text = "STR", fontSize = 20.sp)

                        Spacer(modifier = Modifier.height(8.dp)) // <--- แก้ไข 3: เว้นระยะห่าง

                        Text(text = str.toString(), fontSize = 20.sp)

                        Button(
                            onClick = { str = str - 1 },
                            colors = myButtonColor // <--- แก้ไข 2: ใส่สีปุ่ม
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_arrow_drop_down_24),
                                contentDescription = "down"
                            )
                        }
                    }

                    // --- AGI Column ---
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Button(
                            onClick = { agi = agi + 1 },
                            colors = myButtonColor
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_arrow_drop_up_24),
                                contentDescription = "up"
                            )
                        }
                        Text(text = "AGI", fontSize = 20.sp)

                        Spacer(modifier = Modifier.height(8.dp)) // <--- แก้ไข 3: เว้นระยะห่าง

                        Text(text = agi.toString(), fontSize = 20.sp)
                        Button(
                            onClick = { agi = agi - 1 },
                            colors = myButtonColor
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_arrow_drop_down_24),
                                contentDescription = "down"
                            )
                        }
                    }

                    // --- INT Column ---
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Button(
                            onClick = { int = int + 1 },
                            colors = myButtonColor
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_arrow_drop_up_24),
                                contentDescription = "up"
                            )
                        }
                        Text(text = "INT", fontSize = 20.sp)

                        Spacer(modifier = Modifier.height(8.dp)) // <--- แก้ไข 3: เว้นระยะห่าง

                        Text(text = int.toString(), fontSize = 20.sp)
                        Button(
                            onClick = { int = int - 1 },
                            colors = myButtonColor
                        ) {
                            Image(
                                painter = painterResource(R.drawable.outline_arrow_drop_down_24),
                                contentDescription = "down"
                            )
                        }
                    }

                }
            }
        }
    }
}