package com.example.awaq1.view

import android.content.Intent
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
import com.example.awaq1.data.formularios.FormularioUnoEntity
import kotlinx.coroutines.runBlocking
import androidx.compose.material.icons.Icons
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.material.icons.filled.Add
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.toRoute
import coil.compose.rememberAsyncImagePainter
import com.example.awaq1.ViewModels.CameraViewModel
import com.example.awaq1.data.formularios.FormularioDosEntity
import com.example.awaq1.data.formularios.ImageEntity
import com.example.awaq1.data.formularios.Ubicacion
import com.example.awaq1.navigator.FormUnoID
import kotlinx.coroutines.flow.first
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun Preview() {
    ObservationForm(rememberNavController())
}

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ObservationForm(navController: NavController, formularioId: Long = 0L) {
    val context = LocalContext.current as MainActivity
    val appContainer = context.container

    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    val ubicacion = Ubicacion(context)

    var transecto by remember { mutableStateOf("") }
    var clima by remember { mutableStateOf("") }
    var temporada by remember { mutableStateOf("Verano/Seca") }
    var tipoAnimal by remember { mutableStateOf("") }
    var nombreComun by remember { mutableStateOf("") }
    var nombreCientifico by remember { mutableStateOf("") }
    var numeroIndividuos by remember { mutableStateOf("") }
    var tipoObservacion by remember { mutableStateOf("") }
    var observaciones by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var editado by remember { mutableStateOf("") }
    val cameraViewModel: CameraViewModel = viewModel()
    var showCamera by remember { mutableStateOf(false) }
    val savedImageUris = remember { mutableStateOf(mutableListOf<Uri>()) }

    if (formularioId != 0L) {
        val formulario: FormularioUnoEntity? = runBlocking {
            Log.d("Formulario1Loading", "Loading formulario1 with ID $formularioId")
            appContainer.formulariosRepository.getFormularioUnoStream(formularioId).first()
        }

        if (formulario != null) {
            transecto = formulario.transecto
            clima = formulario.clima
            temporada = formulario.temporada
            tipoAnimal = formulario.tipoAnimal
            nombreComun = formulario.nombreComun
            nombreCientifico = formulario.nombreCientifico
            numeroIndividuos = formulario.numeroIndividuos
            tipoObservacion = formulario.tipoObservacion
            observaciones = formulario.observaciones
            fecha = formulario.fecha
            editado = formulario.editado
            location = if (formulario.latitude != null && formulario.longitude != null) {
                Pair(formulario.latitude, formulario.longitude)
            } else {
                null
            }

            // Load saved images
            val storedImages = runBlocking {
                appContainer.formulariosRepository.getImagesByFormulario(formularioId, "Formulario1")
                    .first() // Fetch the list of ImageEntity for this form
            }

            // Convert image URIs from String to Uri and store them in savedImageUris
            savedImageUris.value = storedImages.mapNotNull { imageEntity ->
                try {
                    Uri.parse(imageEntity.imageUri) // Convert String to Uri
                } catch (e: Exception) {
                    Log.e("ObservationForm", "Failed to parse URI: ${imageEntity.imageUri}", e)
                    null
                }
            }.toMutableList()
        } else {
            Log.e("Formulario1Loading", "NO se pudo obtener el formulario1 con id $formularioId")
        }
    }


    if(location == null){
        LaunchedEffect(Unit) {
            context.requestLocationPermission()
            if (ubicacion.hasLocationPermission()) {
                location = ubicacion.obtenerCoordenadas()
                if (location != null) {
                    Log.d("ObservationForm", "Location retrieved: Lat=${location!!.first}, Long=${location!!.second}")
                } else {
                    Log.d("ObservationForm", "Location is null")
                }
            } else {
                Log.d("ObservationForm", "Location permission required but not granted.")
            }
        }
    }
    // Para Room, 0 significa que no hay un id designado. Genera una nueva entrada con id auto-generado.
    // Un valor de un id existente, reemplaza.


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Formulario de Observación",
                        style = TextStyle(
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = (-0.03).em,
                            color = Color(0xFF4E7029)
                        )
                    )
                },
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
                    savedImageUris = savedImageUris, // Pass state
                    onClose = { showCamera = false },
                    onGalleryClick = { /* Optional: Handle gallery selection */ }
                )
            }  else {
                Box(
                   modifier = Modifier.background(color = Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(16.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .background(color = Color.White),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        location?.let { (latitude, longitude) ->
                            Text("Ubicacion Actual: Lati: $latitude, Long: $longitude")
                        } ?: Text("Buscando ubicacion...")

                        OutlinedTextField(
                            value = transecto,
                            onValueChange = { transecto = it },
                            label = { Text("Número de Transecto") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text("Estado del Tiempo:")
                        FlowRow (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            maxItemsInEachRow = 3
                            //verticalAlignment = Alignment.CenterVertically
                        ) {
                            val weatherOptions =
                                listOf("Soleado", "Parcialmente Nublado", "Lluvioso")
                            val weatherIcons = listOf(
                                R.drawable.sunny, // Add sunny icon in your drawable resources
                                R.drawable.cloudy, // Add partly cloudy icon in your drawable resources
                                R.drawable.rainy // Add rainy icon in your drawable resources
                            )

                            weatherOptions.forEachIndexed { index, option ->
                                IconToggleButton(
                                    checked = clima == option,
                                    onCheckedChange = { clima = option },
                                    modifier = Modifier.size(150.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .border(
                                                width = 2.dp,
                                                color = if (clima == option) Color(0xFF4E7029) else Color.Transparent,
                                                shape = RoundedCornerShape(8.dp)
                                            )
                                            .padding(8.dp)
                                    ) {
                                        Image(
                                            painter = painterResource(id = weatherIcons[index]),
                                            contentDescription = option,
                                            modifier = Modifier.requiredSize(95.dp)
                                        )
                                    }
                                }
                            }
                        }
                        Text("Época")
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val seasonOptions = listOf("Verano/Seca", "Invierno/Lluviosa")
                            seasonOptions.forEach { option ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = temporada == option,
                                        onClick = { temporada = option },
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
                        FlowRow (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround,
                            maxItemsInEachRow = 3
                            //verticalAlignment = Alignment.CenterVertically
                        ) {
                            val animals = listOf("Mamífero", "Ave", "Reptil", "Anfibio", "Insecto")
                            if (tipoAnimal == "") {
                                tipoAnimal = animals[0]
                            }

                            animals.forEach { animal ->
                                IconToggleButton(
                                    checked = tipoAnimal == animal,
                                    onCheckedChange = { tipoAnimal = animal },
                                    modifier = Modifier.size(155.dp)
                                ) {
                                    val imageResource = when (animal) {
                                        "Mamífero" -> R.drawable.ic_mamifero
                                        "Ave" -> R.drawable.ic_ave
                                        "Reptil" -> R.drawable.ic_reptil
                                        "Anfibio" -> R.drawable.ic_anfibio
                                        "Insecto" -> R.drawable.ic_insecto
                                        else -> android.R.drawable.ic_menu_gallery
                                    }

                                    // Outer Box for border and padding
                                    Box(
                                        modifier = Modifier
                                            .padding(8.dp) // Space between items
                                            .border(
                                                width = 2.dp,
                                                color = if (tipoAnimal == animal) Color(0xFF4E7029) else Color.Transparent, // Green border if selected
                                                shape = androidx.compose.foundation.shape.RoundedCornerShape(
                                                    8.dp
                                                ) // Rounded corners
                                            )
                                            .padding(8.dp) // Padding inside the border
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally // Center image and label
                                        ) {
                                            // Image with increased size
                                            Image(
                                                painter = painterResource(id = imageResource),
                                                contentDescription = animal,
                                                modifier = Modifier.requiredSize(75.dp) // Larger size for the image
                                            )
                                            // Label below the image
                                            Text(
                                                text = animal,
                                                fontSize = 20.sp,
                                                color = if (tipoAnimal == animal) Color(0xFF4E7029) else Color(
                                                    0xFF3F3F3F
                                                ), // Green if selected
                                                modifier = Modifier.padding(top = 4.dp) // Space between image and label
                                            )
                                        }
                                    }
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
                        val observacionOptions =
                            listOf("La Vió", "Huella", "Rastro", "Cacería", "Le dijeron")
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


                        // Camera Button
                        Button(
                            onClick = { showCamera = true },
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
                            Text("Tomar Foto")
                        }

                        // Log.d("ObservationForm", "savedImageUri: ${savedImageUri.value}")

                        // Display the saved image
                        savedImageUris.value.forEach { uri ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Image(
                                    painter = rememberAsyncImagePainter(model = uri),
                                    contentDescription = "Saved Image",
                                    modifier = Modifier.size(100.dp)
                                )
                                Button(
                                    onClick = {
                                        savedImageUris.value = savedImageUris.value.toMutableList()
                                            .apply { remove(uri) }
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent // Removes background color
                                    ),
                                    elevation = null // Removes shadow/elevation for a completely flat button
                                ) {
                                    Text(
                                        text = "X",
                                        color = Color.Red,
                                        fontSize = 20.sp
                                    )
                                }
                            }
                        }
                        OutlinedTextField(
                            value = observaciones,
                            onValueChange = { observaciones = it },
                            label = { Text("Observaciones") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp),
                            maxLines = 4
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { navController.navigate("elegir_reporte") },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF4E7029),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(50),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    "Atras",
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }

                            Button(
                                onClick = {
                                    if (fecha.isNullOrEmpty()) {
                                        fecha = getCurrentDate()
                                    }
                                    editado = getCurrentDate()
                                    val formulario =
                                        FormularioUnoEntity(
                                            transecto = transecto,
                                            clima = clima,
                                            temporada = temporada,
                                            tipoAnimal = tipoAnimal,
                                            nombreComun = nombreComun,
                                            nombreCientifico = nombreCientifico,
                                            numeroIndividuos = numeroIndividuos,
                                            tipoObservacion = tipoObservacion,
                                            observaciones = observaciones,
                                            latitude = location?.first,
                                            longitude = location?.second,
                                            fecha = fecha,
                                            editado = editado
                                        ).withID(formularioId)
                                    // Guardar en base de datos, vinculado al usuario
                                    runBlocking {
                                        // Insert regresa su id
                                        val formId =
                                            appContainer.usuariosRepository.insertUserWithFormularioUno(
                                                context.accountInfo.user_id, formulario
                                            )
                                        Log.d("ImageDAO", "formId: $formId")

                                        // Borrar todas las fotos en ese reporte
                                        appContainer.formulariosRepository.deleteImagesByFormulario(
                                            formularioId = formId,
                                            formularioType = "Formulario1"
                                        )

                                        // Agregar todas las imagenes al reporte
                                        savedImageUris.value.forEach { uri ->
                                            val image = ImageEntity(
                                                formularioId = formId,
                                                formularioType = "Formulario1",
                                                imageUri = uri.toString()
                                            )
                                            appContainer.formulariosRepository.insertImage(image)
                                        }
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
                                Text(
                                    "Guardar",
                                    style = TextStyle(
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    )
}

fun getCurrentDate(existingDate: String? = null): String {
    return existingDate ?: run {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = Date()
        dateFormat.format(date)
    }
}