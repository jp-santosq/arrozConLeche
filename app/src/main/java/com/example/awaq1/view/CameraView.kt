package com.example.awaq1.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.awaq1.MainActivity
import com.example.awaq1.ViewModels.CameraViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CameraView(modifier: Modifier = Modifier, activity: MainActivity) {
    val cameraViewModel = CameraViewModel()
    var showCamera by remember { mutableStateOf(false) }

    Column(modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
        if (showCamera) {
            CameraWindow(activity, cameraViewModel, onClose = { showCamera = false })
        } else {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(onClick = { showCamera = true }) {
                    Text(text = "Tomar foto")
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES .P)
@Composable
fun CameraWindow(activity: MainActivity, cameraViewModel: CameraViewModel, onClose: () -> Unit) {
    val imageCapture = remember { ImageCapture.Builder().setFlashMode(ImageCapture.FLASH_MODE_ON).build() }
    val controller = remember {
        LifecycleCameraController(activity).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        }
    }

    cameraViewModel.setImageCapture(controller)

    var flashVisible by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) } // Estado para el diálogo
    var savedImageUri by remember { mutableStateOf<Uri?>(null) } // URI de la imagen guardada

    Box(modifier = Modifier.fillMaxSize().background(Color.Black)) {
        val lifecycleOwner = LocalLifecycleOwner.current
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                PreviewView(it).apply {
                    this.controller = controller
                    controller.bindToLifecycle(lifecycleOwner)
                }
            }
        )

        // Efecto de flash
        if (flashVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.5f))
                    .clickable { /* Evitar clics en el flash */ }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 80.dp)
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Botón para cerrar la cámara
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .size(45.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        onClose() // Cierra la cámara
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Close Camera",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.width(1.dp))

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        if ((activity as MainActivity).arePermissionsGranted()) {
                            cameraViewModel.takePhoto(
                                context = activity,
                                onImageSaved = { file ->
                                    // Guardar la URI de la imagen
                                    savedImageUri = Uri.fromFile(file) // Corrección aquí
                                    // Mostrar el diálogo
                                    showDialog = true
                                    // Mostrar el efecto de flash
                                    flashVisible = true
                                    // Ocultar el efecto de flash después de un breve tiempo
                                    GlobalScope.launch {
                                        delay(100) // Duración del efecto de flash
                                        flashVisible = false
                                    }
                                },
                                onError = { exception ->
                                    // Manejar error
                                    println("Error al guardar la foto: ${exception.message}")
                                }
                            )
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = "Take Photo",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.width(1.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .size(45.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        controller.cameraSelector =
                            if (controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA) {
                                CameraSelector.DEFAULT_FRONT_CAMERA
                            } else {
                                CameraSelector.DEFAULT_BACK_CAMERA
                            }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Change Camera",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(26.dp)
                )
            }
        }

        // Diálogo de confirmación
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "¿Usar esta foto?") },
                text = { Text(text = "¿Quieres usar la foto que acabas de tomar?") },
                confirmButton = {
                    Button(onClick = {
                        // Aquí puedes agregar la lógica para subir la imagen al almacenamiento
                        println("Foto subida: ${savedImageUri?.path}")
                        showDialog = false
                    }) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        // Aquí puedes manejar la lógica si el usuario decide no usar la foto println("Foto no utilizada")
                        showDialog = false
                    }) {
                        Text("No")
                    }
                }
            )
        }
    }
}