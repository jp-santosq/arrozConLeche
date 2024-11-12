package com.example.awaq1.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFFFF)) // Background color similar to screenshot
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Encabezado
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp) // Adjust the height as needed
                        .clip(RoundedCornerShape(bottomStart = 300.dp, bottomEnd = 300.dp)) // Rounded bottom corners
                        .background(Color(0xFFCDE4B4)), // Light green color
                    contentAlignment = Alignment.Center // Center the text within the box
                ) {
                    Text(
                        text = "Hola, Samantha",
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4E7029) // Darker green text color
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Gráfico de Progreso

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    // Dashboard Title
                    Text(
                        text = "Dashboard",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333), // Darker color
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    // Emergency Box
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFF8D7DA), shape = RoundedCornerShape(8.dp)) // Light red background with rounded corners
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "🚨 Emergencia\nTienes 2 formularios sin subir a la nube",
                            color = Color(0xFF721C24), // Dark red text color
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Progress Indicator and Text
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        // Form Statistics
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            StatsColumn(label = "Total", count = 5, color = Color.Black)
                            StatsColumn(label = "Enviados", count = 3, color = Color(0xFF4CAF50)) // Green for sent
                            StatsColumn(label = "Guardados", count = 2, color = Color.Red) // Red for saved
                        }

                        // Circular Progress Indicator
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            // Progress bar container with a light gray background
                            Box(
                                modifier = Modifier
                                    .weight(1f) // Takes up available width
                                    .height(13.dp) // Set desired height for the progress bar
                                    .background(Color(0xFFF3F3F3), shape = RoundedCornerShape(12.dp)) // Rounded corners
                            ) {
                                // Inner progress indicator
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight() // Matches the height of the outer box
                                        .background(Color(0xFF4E7029), shape = RoundedCornerShape(12.dp)) // Green color
                                        .fillMaxWidth(0.6f) // Set width to 60% progress
                                )
                            }

                            Spacer(modifier = Modifier.width(8.dp)) // Space between bar and text

                            // Progress percentage text
                            Text(
                                text = "60%",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF4E7029) // Green color matching the bar
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun StatsColumn(label: String, count: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "$count",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            fontSize = 14.sp,
            color = Color.Gray
        )
    }
}


