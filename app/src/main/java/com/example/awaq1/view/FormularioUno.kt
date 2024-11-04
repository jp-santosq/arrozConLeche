package com.example.awaq1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.awaq1.MainActivity
import com.example.awaq1.R
import com.example.awaq1.data.formularioUno.FormularioUnoEntity
import kotlinx.coroutines.runBlocking

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun Preview() {
    ObservationForm(rememberNavController())
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObservationForm(navController: NavController) {
    val context = LocalContext.current as MainActivity
    val appContainer = context.container

    var transecto by remember { mutableStateOf("") }
    var tipoAnimal by remember { mutableStateOf("") }
    var nombreComun by remember { mutableStateOf("") }
    var nombreCientifico by remember { mutableStateOf("") }
    var numeroIndividuos by remember { mutableStateOf("") }
    var tipoObservacion by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text ="Formulario de Observación",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.03).em, // -3% se traduce aproximadamente a -0.07 em
                        color = Color(0xFF4E7029) // Color verde en hexadecimal
                    )
                ) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White, // White background for the top bar
                    titleContentColor = Color(0xFF4E7029) // Green color for the title content
                )
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()
                .background(Color.White)) {
               // Image(painter = painterResource(R.drawable.background),
                 //   contentDescription = null,
                   // contentScale = ContentScale.FillBounds,
                   // modifier = Modifier.matchParentSize())
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(16.dp)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),  // Added scrollable behavior
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Número de transecto input
                    OutlinedTextField(
                        value = transecto,
                        onValueChange = { transecto = it },
                        label = { Text("Número de Transecto") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Tipo de Animal (Multiple Choice with Icons)
                    Text("Tipo de Animal")
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val animals = listOf("Mamífero", "Ave", "Reptil", "Anfibio", "Insecto")
                        animals.forEach { animal ->
                            IconToggleButton(
                                checked = tipoAnimal == animal,
                                onCheckedChange = { tipoAnimal = animal },
                            ) {
                                val iconResource = when (animal) {
                                    "Mamífero" -> R.drawable.ic_mamifero // Replace with your actual resource ID for Mamífero
                                    "Ave" -> R.drawable.ic_ave // Replace with your actual resource ID for Ave
                                    "Reptil" -> R.drawable.ic_reptil // Replace with your actual resource ID for Reptil
                                    "Anfibio" -> R.drawable.ic_anfibio // Replace with your actual resource ID for Anfibio
                                    "Insecto" -> R.drawable.ic_insecto // Replace with your actual resource ID for Insecto
                                    else -> android.R.drawable.ic_menu_gallery // Fallback resource
                                }
                                Icon(
                                    painter = painterResource(id = iconResource),
                                    contentDescription = animal,
                                    modifier = Modifier.size(40.dp),
                                    tint = if (tipoAnimal == animal) Color(0xFF4E7029) else Color(0xFF3F3F3F)
                                )
                            }
                        }
                    }

                    // Nombre común
                    OutlinedTextField(
                        value = nombreComun,
                        onValueChange = { nombreComun = it },
                        label = { Text("Nombre Común") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Nombre científico
                    OutlinedTextField(
                        value = nombreCientifico,
                        onValueChange = { nombreCientifico = it },
                        label = { Text("Nombre Científico") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Número de Individuos
                    OutlinedTextField(
                        value = numeroIndividuos,
                        onValueChange = { numeroIndividuos = it },
                        label = { Text("Número de Individuos") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Tipo de Observación (Multiple Choice)
                    Text("Tipo de Observación")
                    val observacionOptions = listOf("La Vió", "Huella", "Rastro", "Cacería", "Le dijeron")
                    Column {
                        observacionOptions.forEach { option ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(
                                    selected = tipoObservacion == option,
                                    onClick = { tipoObservacion = option },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color(0xFF4E7029), // Green color for selected state
                                        unselectedColor = Color.Gray // Optional: color for unselected state
                                    )
                                )
                                Text(option, modifier = Modifier.padding(start = 8.dp))
                            }
                        }
                    }

                    // Elige Archivo button
                    Button(onClick = { /* Logic to pick file */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4E7029), // Green background color
                            contentColor = Color.White // White text color
                        ),
                        shape = RoundedCornerShape(10) ) {
                        Text("Elige Archivo")
                    }

                    // Observaciones (multiline text box)
                    OutlinedTextField(
                        value = observaciones,
                        onValueChange = { observaciones = it },
                        label = { Text("Observaciones") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        maxLines = 4
                    )

                    // Atrás and Enviar buttons
                    Row(
                        modifier = Modifier.fillMaxWidth()
                            .padding(horizontal = 16.dp), // Add some horizontal padding if needed
                        horizontalArrangement = Arrangement.spacedBy(8.dp) // Space between buttons
                    ) {
                        Button(
                            onClick = { navController.navigate("settings") },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4E7029), // Green background
                                contentColor = Color.White // White text color
                            ),
                            shape = RoundedCornerShape(50), // Rounded corners
                            modifier = Modifier
                                .weight(1f) // Each button takes up half the width
                        ) {
                            Text("Atras",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                ))
                        }

                        Button(onClick = {
                            // Aquí se guarda el reporte existente en la base de datos SQLite
                            runBlocking {
                                appContainer.formularioUnoRepository.insertFormularioUno(
                                    FormularioUnoEntity(
                                        transecto = transecto,
                                        tipoAnimal = tipoAnimal,
                                        nombreComun = nombreComun,
                                        nombreCientifico = nombreCientifico,
                                        numeroIndividuos = numeroIndividuos,
                                        tipoObservacion = tipoObservacion,
                                        observaciones = observaciones
                                        )
                                )
                            }
                            navController.navigate("settings")

                        },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4E7029), // Green background
                                contentColor = Color.White // White text color
                            ),
                            shape = RoundedCornerShape(50), // Rounded corners
                            modifier = Modifier
                                .weight(1f) // Each button takes up half the width
                        ) {
                            Text("Enviar",
                                style = TextStyle(
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.SemiBold
                                ))
                        }
                    }
                }
            }

        }
    )
}


