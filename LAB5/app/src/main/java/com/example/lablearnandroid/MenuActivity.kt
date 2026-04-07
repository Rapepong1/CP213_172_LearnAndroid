package com.example.lablearnandroid

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityOptionsCompat
import com.example.lablearnandroid.architecture.mvi.MviCounterActivity
import com.example.lablearnandroid.architecture.mvvm.MvvmCounterActivity

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .padding(top = 32.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Menu with Activity Transitions",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // 1. Fade Transition (Android Default)
                    MenuButton(
                        label = "Gallery (Fade)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, GalleryActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(
                                this@MenuActivity,
                                android.R.anim.fade_in,
                                android.R.anim.fade_out
                            )
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 2. Slide Left/Right (Android Default)
                    MenuButton(
                        label = "Sensors (Slide Left-Right)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, SensorActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(
                                this@MenuActivity,
                                android.R.anim.slide_in_left,
                                android.R.anim.slide_out_right
                            )
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 3. Slide Up (Custom Anim)
                    MenuButton(
                        label = "GPS Activity (Custom Slide Up)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, GpsActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(
                                this@MenuActivity,
                                R.anim.slide_in_up,
                                R.anim.no_animation
                            )
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 4. Slide Right (Custom Anim)
                    MenuButton(
                        label = "RPG Card (Custom Slide Right)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, RPGCardActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(
                                this@MenuActivity,
                                R.anim.slide_in_right,
                                R.anim.no_animation
                            )
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 5. Custom Fade In
                    MenuButton(
                        label = "Pokedex (Custom Fade In)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, PokedexActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(
                                this@MenuActivity,
                                R.anim.fade_in,
                                R.anim.no_animation
                            )
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 6. Slide Left-Right (Requested example)
                    MenuButton(
                        label = "Lifecycle Compose (Slide Left-Right)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, LifeCycleComposeActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(
                                this@MenuActivity, 
                                android.R.anim.slide_in_left, 
                                android.R.anim.slide_out_right
                            )
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 7. MVVM Counter (Default Transition)
                    MenuButton(
                        label = "MVVM Counter (Default)",
                        onClick = {
                            startActivity(Intent(this@MenuActivity, MvvmCounterActivity::class.java))
                        }
                    )

                    // 8. MVI Counter (Fade)
                    MenuButton(
                        label = "MVI Counter (Fade)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, MviCounterActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, android.R.anim.fade_in, android.R.anim.fade_out)
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 9. Animation Part 1 (Slide Up)
                    MenuButton(
                        label = "Animation Part 1 (Slide Up)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, Part1AnimationActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, R.anim.slide_in_up, R.anim.no_animation)
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 10. Contact List (Slide Right)
                    MenuButton(
                        label = "Contact List (Slide Right)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, ContactListActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, R.anim.slide_in_right, R.anim.no_animation)
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 11. Donut Chart (Fade)
                    MenuButton(
                        label = "Donut Chart (Fade)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, DonutChartActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, R.anim.fade_in, R.anim.no_animation)
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 12. Swipe to Dismiss (Slide Up)
                    MenuButton(
                        label = "Swipe to Dismiss (Slide Up)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, SwipeToDismissActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, R.anim.slide_in_up, R.anim.no_animation)
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 13. Snackbar Effect (Default)
                    MenuButton(
                        label = "Snackbar Effect (Default)",
                        onClick = {
                            startActivity(Intent(this@MenuActivity, SnackbarEffectActivity::class.java))
                        }
                    )

                    // 14. WebView (Slide Right)
                    MenuButton(
                        label = "WebView Browser (Slide Right)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, WebViewActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, R.anim.slide_in_right, R.anim.no_animation)
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 15. Transition Main (Slide Up)
                    MenuButton(
                        label = "Transition Main (Slide Up)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, TransitionMainActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, R.anim.slide_in_up, R.anim.no_animation)
                            startActivity(intent, options.toBundle())
                        }
                    )

                    // 16. Responsive Profile (Fade)
                    MenuButton(
                        label = "Responsive Profile (Fade)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, ResponsiveProfileActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, R.anim.fade_in, R.anim.no_animation)
                            startActivity(intent, options.toBundle())
                        }
                    )

                    MenuButton(
                        label = "Collapsing Layout (Fade)",
                        onClick = {
                            val intent = Intent(this@MenuActivity, CollapsingLayoutActivity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, R.anim.fade_in, R.anim.no_animation)
                            startActivity(intent, options.toBundle())
                        }
                    )

                    MenuButton(
                        label = "Part10",
                        onClick = {
                            val intent = Intent(this@MenuActivity, Part10Activity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, R.anim.fade_in, R.anim.no_animation)
                            startActivity(intent, options.toBundle())
                        }
                    )

                    MenuButton(
                        label = "Part11",
                        onClick = {
                            val intent = Intent(this@MenuActivity, Part11Activity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, R.anim.fade_in, R.anim.no_animation)
                            startActivity(intent, options.toBundle())
                        }
                    )

                    MenuButton(
                        label = "Part12",
                        onClick = {
                            val intent = Intent(this@MenuActivity, Part12Activity::class.java)
                            val options = ActivityOptionsCompat.makeCustomAnimation(this@MenuActivity, R.anim.fade_in, R.anim.no_animation)
                            startActivity(intent, options.toBundle())
                        }
                    )
                }
            }
        }
    }

    @Composable
    fun MenuButton(label: String, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Text(text = label)
        }
    }
}
