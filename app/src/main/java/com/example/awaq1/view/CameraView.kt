package com.example.awaq1.view

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.Face
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
import androidx.compose.ui.platform.LocalContext
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
fun CameraWindow(
    activity: MainActivity,
    cameraViewModel: CameraViewModel,
    onClose: () -> Unit,
    onGalleryClick: () -> Unit
) {
    val imageCapture = remember { ImageCapture.Builder().setFlashMode(ImageCapture.FLASH_MODE_ON).build() }
    val controller = remember {
        LifecycleCameraController(activity).apply {
            setEnabledUseCases(CameraController.IMAGE_CAPTURE)
        }
    }

    cameraViewModel.setImageCapture(controller)

    var flashVisible by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var savedImageUri by remember { mutableStateOf<Uri?>(null) }
    var showGalleryDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            savedImageUri = it
            showGalleryDialog = true
        }
    }

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

        if (flashVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.5f))
                    .clickable { }
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
                    .clickable { onClose() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Close Camera",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(26.dp)
                )
            }

            // Botón para tomar foto
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        if ((activity as MainActivity).arePermissionsGranted()) {
                            cameraViewModel.takePhoto(
                                context = activity,
                                onImageSaved = { uri ->
                                    savedImageUri = uri
                                    showDialog = true
                                    flashVisible = true
                                    GlobalScope.launch {
                                        delay(100)
                                        flashVisible = false
                                    }
                                },
                                onError = { exception ->
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

            // Botón para seleccionar de galería
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .size(45.dp)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        galleryLauncher.launch("image/*")
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Select from Gallery",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(26.dp)
                )
            }
        }

        // Diálogo para foto tomada
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "¿Usar esta foto?") },
                text = { Text(text = "¿Quieres usar la foto que acabas de tomar?") },
                confirmButton = {
                    Button(onClick = {
                        savedImageUri?.let { uri ->
                            activity.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).apply {
                                data = uri
                            })
                        }
                        println("Foto guardada en la galería: ${savedImageUri?.path}")
                        showDialog = false
                    }) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        showDialog = false
                    }) {
                        Text("No")
                    }
                }
            )
        }

        // Diálogo para imagen seleccionada de galería
        if (showGalleryDialog) {
            AlertDialog(
                onDismissRequest = { showGalleryDialog = false },
                title = { Text(text = "¿Usar esta imagen?") },
                text = { Text(text = "¿Quieres usar la imagen seleccionada?") },
                confirmButton = {
                    Button(onClick = {
                        savedImageUri?.let { uri ->
                            // Guardar la imagen seleccionada en la memoria interna
                            cameraViewModel.saveGalleryImage(
                                context = context,
                                sourceUri = uri,
                                onImageSaved = { savedUri ->
                                    activity.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).apply {
                                        data = savedUri
                                    })
                                    println("Imagen de galería guardada: ${savedUri.path}")
                                },
                                onError = { exception ->
                                    println("Error al guardar la imagen de galería: ${exception.message}")
                                }
                            )
                        }
                        showGalleryDialog = false
                    }) {
                        Text("Sí")
                    }
                },
                dismissButton = {
                    Button(onClick = {
                        savedImageUri = null
                        showGalleryDialog = false
                    }) {
                        Text("No")
                    }
                }
            )
        }
    }
}