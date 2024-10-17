package com.example.awaq1.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObservationForm(navController: NavController) {
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
                title = { Text("Formulario de Observación") }
            )
        },
        content = { paddingValues ->
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
                            Icon(
                                painter = painterResource(id = /* replace with your image resource ID */ android.R.drawable.ic_menu_gallery),
                                contentDescription = animal,
                                tint = if (tipoAnimal == animal) Color.Blue else Color.Gray,
                                modifier = Modifier.size(40.dp)
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
                                onClick = { tipoObservacion = option }
                            )
                            Text(option, modifier = Modifier.padding(start = 8.dp))
                        }
                    }
                }

                // Elige Archivo button
                Button(onClick = { /* Logic to pick file */ }) {
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
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(onClick = { navController.navigate("settings")}) {
                        Text("Atrás")
                    }
                    Button(onClick = { /* Logic for 'Enviar' */ }) {
                        Text("Enviar")
                    }
                }
            }
        }
    )
}


