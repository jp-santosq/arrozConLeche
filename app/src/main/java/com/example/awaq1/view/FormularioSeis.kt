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
import androidx.compose.foundation.border
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.awaq1.ViewModels.CameraViewModel
import com.example.awaq1.data.formularios.FormularioSeisEntity
import com.example.awaq1.data.formularios.ImageEntity
import com.example.awaq1.data.formularios.Ubicacion
import com.google.gson.Gson
import kotlinx.coroutines.flow.first


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewForm6() {
    ObservationFormSeis(rememberNavController())
}

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObservationFormSeis(navController: NavController, formularioId: Long = 0) {
    val context = LocalContext.current as MainActivity
    val appContainer = context.container
    var showCamera by remember { mutableStateOf(false) }
    val cameraViewModel: CameraViewModel = viewModel()
    val savedImageUris = remember { mutableStateOf(mutableListOf<Uri>()) }
    var location by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    val ubicacion = Ubicacion(context)

    var codigo by remember { mutableStateOf("") }
    var clima by remember { mutableStateOf("") }
    var temporada by remember { mutableStateOf("Verano/Seca") }
    var zona by remember { mutableStateOf("") }
    var nombreCamara by remember { mutableStateOf("") }
    var placaCamara by remember { mutableStateOf("") }
    var placaGuaya by remember { mutableStateOf("") }
    var anchoCamino by remember { mutableStateOf("") }
    var fechaInstalacion by remember { mutableStateOf("") }
    var distanciaObjetivo by remember { mutableStateOf("") }
    var alturaLente by remember { mutableStateOf("") }
    val checklistItems = listOf(
        "Programada",
        "Memoria",
        "Prueba de gateo",
        "Instalada",
        "Letrero de cámara",
        "Prendida"
    )
    val checklistState = remember { mutableStateMapOf<String, Boolean>().apply { checklistItems.forEach { put(it, false) } } }
    var observaciones: String by remember { mutableStateOf("") }

    if (formularioId != 0L) {
        val formulario: FormularioSeisEntity? = runBlocking {
            Log.d("Formulario6Loading", "Loading formulario6 with ID $formularioId")
            appContainer.formulariosRepository.getFormularioSeisStream(formularioId).first()
        }

        if (formulario != null) {
            codigo = formulario.codigo
            zona = formulario.zona
            clima = formulario.clima
            temporada = formulario.temporada
            nombreCamara = formulario.nombreCamara
            placaCamara = formulario.placaCamara
            placaGuaya = formulario.placaGuaya
            anchoCamino = formulario.anchoCamino
            fechaInstalacion = formulario.fechaInstalacion
            distanciaObjetivo = formulario.distanciaObjetivo
            alturaLente = formulario.alturaLente
            observaciones = formulario.observaciones
            location = if (formulario.latitude != null && formulario.longitude != null) {
                Pair(formulario.latitude, formulario.longitude)
            } else {
                null
            }
            val restoredChecklist: Map<String, Boolean> = Gson().fromJson(
                formulario.checklist,
                object : com.google.gson.reflect.TypeToken<Map<String, Boolean>>() {}.type // Explicitly define the type
            )

            restoredChecklist.forEach { (item, isChecked) ->
                checklistState[item] = isChecked // No type mismatch now
            }
            // Load saved images
            val storedImages = runBlocking {
                appContainer.formulariosRepository.getImagesByFormulario(formularioId, "Formulario6")
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
            Log.e("Formulario6Loading", "NO se pudo obtener el formulario6 con id $formularioId")
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
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .padding(16.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        location?.let { (latitude, longitude) ->
                            Text("Ubicacion Actual: Lati: $latitude, Long: $longitude")
                        } ?: Text("Buscando ubicacion...")

                        OutlinedTextField(
                            value = codigo,
                            onValueChange = { codigo = it },
                            label = { Text("Código") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text("Estado del Tiempo:")
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val weatherOptions = listOf("Soleado", "Parcialmente Nublado", "Lluvioso")
                            val weatherIcons = listOf(
                                R.drawable.sunny,
                                R.drawable.cloudy,
                                R.drawable.rainy
                            )

                            weatherOptions.forEachIndexed { index, option ->
                                IconToggleButton(
                                    checked = clima == option,
                                    onCheckedChange = { clima = option },
                                    modifier = Modifier.size(100.dp)
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
                                            modifier = Modifier.requiredSize(64.dp)
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
                        Text("Zona")
                        val zonasOpciones = listOf(
                            "Bosque",
                            "Arreglo Agroforestal",
                            "Cultivos Transitorios",
                            "Cultivos Permanentes"
                        )
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
                        // Nombre Cámara Field
                        OutlinedTextField(
                            value = nombreCamara,
                            onValueChange = { nombreCamara = it },
                            label = { Text("Nombre Cámara") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Placa Cámara Field
                        OutlinedTextField(
                            value = placaCamara,
                            onValueChange = { placaCamara = it },
                            label = { Text("Placa Cámara") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Placa Guaya Field
                        OutlinedTextField(
                            value = placaGuaya,
                            onValueChange = { placaGuaya = it },
                            label = { Text("Placa Guaya") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Ancho Camino Field
                        OutlinedTextField(
                            value = anchoCamino,
                            onValueChange = { anchoCamino = it },
                            label = { Text("Ancho Camino mt") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Fecha de Instalación Field
                        OutlinedTextField(
                            value = fechaInstalacion,
                            onValueChange = { fechaInstalacion = it },
                            label = { Text("Fecha de Instalación") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Distancia al Objetivo Field
                        OutlinedTextField(
                            value = distanciaObjetivo,
                            onValueChange = { distanciaObjetivo = it },
                            label = { Text("Distancia al objetivo mt") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Altura del Lente Field
                        OutlinedTextField(
                            value = alturaLente,
                            onValueChange = { alturaLente = it },
                            label = { Text("Altura del lente mt") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Checklist
                        Text("Lista de Chequeo")
                        checklistItems.forEach { item ->
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = checklistState[item] ?: false,
                                    onCheckedChange = { isChecked -> checklistState[item] = isChecked }
                                )
                                Text(item, modifier = Modifier.padding(start = 8.dp))
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
                            Text("Take Photo")
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
                                Button(onClick = {
                                    savedImageUris.value = savedImageUris.value.toMutableList().apply { remove(uri) }
                                },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color.Transparent // Removes background color
                                    ),
                                    elevation = null // Removes shadow/elevation for a completely flat button
                                ) {
                                    Text(text = "X",
                                        color = Color.Red,
                                        fontSize = 20.sp)
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
                                onClick = { navController.navigate("home") },
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
                                    val checklistJson = Gson().toJson(checklistState)
                                    val formulario =
                                        FormularioSeisEntity(
                                            codigo = codigo,
                                            zona = zona,
                                            clima = clima,
                                            temporada = temporada,
                                            nombreCamara = nombreCamara,
                                            placaCamara = placaCamara,
                                            placaGuaya = placaGuaya,
                                            anchoCamino = anchoCamino,
                                            fechaInstalacion = fechaInstalacion,
                                            distanciaObjetivo = distanciaObjetivo,
                                            alturaLente = alturaLente,
                                            checklist = checklistJson,
                                            observaciones = observaciones,
                                            latitude = location?.first,
                                            longitude = location?.second
                                        ).withID(formularioId)

                                    runBlocking {
                                        // Insert regresa su id
                                        val formId = appContainer.usuariosRepository.insertUserWithFormularioSeis(
                                            context.accountInfo.user_id, formulario
                                        )
                                        Log.d("ImageDAO", "formId: $formId")

                                        // Borrar todas las fotos en ese reporte
                                        appContainer.formulariosRepository.deleteImagesByFormulario(
                                            formularioId = formId,
                                            formularioType = "Formulario6"
                                        )

                                        // Agregar todas las imagenes al reporte
                                        savedImageUris.value.forEach { uri ->
                                            val image = ImageEntity(
                                                formularioId = formId,
                                                formularioType = "Formulario6",
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
                                    "Enviar",
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