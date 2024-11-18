package com.example.awaq1.view

import android.net.Uri
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
import kotlinx.coroutines.runBlocking
import androidx.compose.material.icons.Icons
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.Add
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.awaq1.ViewModels.CameraViewModel
import com.example.awaq1.data.formularios.FormularioDosEntity


@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewForm2() {
    ObservationFormDos(rememberNavController())
}

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObservationFormDos(navController: NavController) {
    val context = LocalContext.current as MainActivity
    val appContainer = context.container
    val cameraViewModel: CameraViewModel = viewModel()

    var zona: String by remember {mutableStateOf("")}
    var tipoAnimal: String by remember {mutableStateOf("")}
    var nombreComun: String by remember {mutableStateOf("")}
    var nombreCientifico: String by remember {mutableStateOf("")}
    var numeroIndividuos: String by remember {mutableStateOf("")}
    var tipoObservacion: String by remember {mutableStateOf("")}
    var alturaObservacion: String by remember {mutableStateOf("")}
    var observaciones: String by remember {mutableStateOf("")}
    var showCamera by remember { mutableStateOf(false) }
    val savedImageUri = remember { mutableStateOf<Uri?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text ="Formulario de Observación",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = (-0.03).em,
                        color = Color(0xFF4E7029)
                    )
                ) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color(0xFF4E7029)
                )
            )
        },
        content = { paddingValues ->
            if (showCamera) {
                CameraWindow(
                    activity = context,
                    cameraViewModel = cameraViewModel,
                    savedImageUri = savedImageUri, // Pass state
                    onClose = { showCamera = false },
                    onGalleryClick = { /* Optional: Handle gallery selection */ }
                )
            } else {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)) {
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(16.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text("Zona")
                        val zonasOpciones = listOf("Bosque", "Arreglo Agroforestal", "Cultivos Transitorios", "Cultivos Permanentes")
                        if (zona == "") {
                            zona = zonasOpciones[0]
                        }
                        Column {
                            zonasOpciones.forEach { option ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = zona == option,
                                        onClick = { zona = option },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color(0xFF4E7029),
                                            unselectedColor = Color.Gray
                                        )
                                    )
                                    Text(option, modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }

                        Text("Tipo de Animal")
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val animals = listOf("Mamífero", "Ave", "Reptil", "Anfibio", "Insecto")
                            if (tipoAnimal == "") {
                                tipoAnimal = animals[0]
                            }
                            animals.forEach { animal ->
                                IconToggleButton(
                                    checked = tipoAnimal == animal,
                                    onCheckedChange = { tipoAnimal = animal },
                                ) {
                                    val iconResource = when (animal) {
                                        "Mamífero" -> R.drawable.ic_mamifero
                                        "Ave" -> R.drawable.ic_ave
                                        "Reptil" -> R.drawable.ic_reptil
                                        "Anfibio" -> R.drawable.ic_anfibio
                                        "Insecto" -> R.drawable.ic_insecto
                                        else -> android.R.drawable.ic_menu_gallery
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

                        OutlinedTextField(
                            value = nombreComun,
                            onValueChange = { nombreComun = it },
                            label = { Text("Nombre Común") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = nombreCientifico,
                            onValueChange = { nombreCientifico = it },
                            label = { Text("Nombre Científico") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = numeroIndividuos,
                            onValueChange = { numeroIndividuos = it },
                            label = { Text("Número de Individuos") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text("Tipo de Observación")
                        val observacionOptions = listOf("La Vió", "Huella", "Rastro", "Cacería", "Le dijeron")
                        if (tipoObservacion == "") {
                            tipoObservacion = observacionOptions[0]
                        }
                        Column {
                            observacionOptions.forEach { option ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = tipoObservacion == option,
                                        onClick = { tipoObservacion = option },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color(0xFF4E7029),
                                            unselectedColor = Color.Gray
                                        )
                                    )
                                    Text(option, modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }

                        Text("Altura de Observación")
                        val alturaOptions: List<Pair<String, String>> = listOf(Pair("Baja", "<1mt"), Pair("Media", "1-3mt"), Pair("Alta", ">3mt"))
                        if (alturaObservacion == "") {
                            alturaObservacion = alturaOptions[0].first
                        }
                        Column {
                            alturaOptions.forEach { option ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = alturaObservacion == option.first,
                                        onClick = { alturaObservacion = option.first },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color(0xFF4E7029),
                                            unselectedColor = Color.Gray
                                        )
                                    )
                                    Text("${option.first} ${option.second}", modifier = Modifier.padding(start = 8.dp))
                                }
                            }
                        }

                        // Camera Button
                        Button(
                            onClick = { showCamera = true }, // Toggle the camera view
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4E7029),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Take Photo",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Take Photo")
                        }

                        Log.d("ObservationForm", "savedImageUri: ${savedImageUri.value}")

                        // Display the saved image
                        savedImageUri.value?.let { uri ->
                            Column {
                                //Text("Image saved at: $uri")
                                Image(
                                    painter = rememberAsyncImagePainter(model = uri),
                                    contentDescription = "Saved Image",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp)
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { navController.navigate("home") },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4E7029),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(50),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Atras",
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold
                                    ))
                            }

                            Button(
                                onClick = {
                                    val formulario =
                                        FormularioDosEntity(
                                            zona = zona,
                                            tipoAnimal = tipoAnimal,
                                            nombreComun = nombreComun,
                                            nombreCientifico = nombreCientifico,
                                            numeroIndividuos = numeroIndividuos,
                                            tipoObservacion = tipoObservacion,
                                            alturaObservacion = alturaObservacion,
                                            observaciones = observaciones,
                                        )
                                    runBlocking {
                                        appContainer.usuariosRepository.insertUserWithFormularioDos(
                                            context.accountInfo.user_id, formulario
                                        )
                                    }

                                    navController.navigate("home")
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4E7029),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(50),
                                modifier = Modifier.weight(1f)
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
        }
    )
}
