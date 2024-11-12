package com.example.awaq1.view
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inicio") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFAED581)) // Color similar al de la imagen
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFFFF))
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Encabezado
                Text(
                    text = "Hola, Samantha",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Gráfico de Progreso
                CircularProgressIndicator(
                    progress = {
                        1.0f // Al 100%
                    },
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp),
                    color = Color(0xFF4CAF50),
                    strokeWidth = 8.dp,
                )

                Text(
                    text = "100%",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Estadísticas de formularios
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    StatsColumn(label = "Forms En Total", count = 5, color = Color.Black)
                    StatsColumn(label = "Forms Subidos", count = 5, color = Color(0xFF4CAF50))
                    StatsColumn(label = "Forms Guardados", count = 0, color = Color.Red)
                }
            }
        }
    )
}

@Composable
fun StatsColumn(label: String, count: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "$count", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = color)
        Text(text = label, fontSize = 14.sp, color = Color.Gray)
    }
}



