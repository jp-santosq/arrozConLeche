package com.example.awaq1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Settings(navController: NavController) {
    var notificationsEnabled by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Configuración") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFFAED581)) // Color similar al de la imagen
            )
        },
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            SectionTitle("GENERAL")
            MenuItem("Editar Perfil") {
                navController.navigate("perfil")
            }
            MenuItem("Cambiar contraseña") {
                // Acción para cambiar contraseña
            }

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle("NOTIFICACIONES")
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Notificaciones", fontSize = 18.sp)
                Spacer(modifier = Modifier.weight(1f))
                Switch(checked = notificationsEnabled, onCheckedChange = { notificationsEnabled = it })
            }

            Spacer(modifier = Modifier.height(16.dp))

            SectionTitle("ACCIONES")
            MenuItem("Cerrar sesión") {
                // Acción para cerrar sesión
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(vertical = 8.dp)
    )
}

@Composable
fun MenuItem(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, fontSize = 18.sp)
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(containerColor = Color(0xFFAED581)) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = null) },
            label = { Text("Inicio") },
            selected = true,
            onClick = { navController.navigate("log_in")}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = null) },
            label = { Text("Búsqueda") },
            selected = false,
            onClick = { navController.navigate("mapa") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Settings, contentDescription = null) },
            label = { Text("Configuración") },
            selected = false,
            onClick = { /* Acción para configuración */ }
        )
    }
}
