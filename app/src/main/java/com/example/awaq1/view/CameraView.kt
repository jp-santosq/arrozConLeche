package com.example.awaq1.view


import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.ViewGroup
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.example.awaq1.MainActivity
import com.example.awaq1.ViewModels.CameraViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun CameraWindow(
    activity: MainActivity,
    cameraViewModel: CameraViewModel,
    savedImageUris: MutableState<MutableList<Uri>>,
    onClose: () -> Unit,
    onGalleryClick: () -> Unit
) {
    var flashVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                savedImageUris.value.add(uri)
                onClose() // Exit the CameraWindow after importing
            }
        }
    )

    var showPreview by remember { mutableStateOf(false) }
    var currentCapturedUri by remember { mutableStateOf<Uri?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Camera Preview
        if (!showPreview) {
            AndroidView(
                factory = { ctx ->
                    PreviewView(ctx).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        cameraViewModel.bindCamera(this, activity)
                    }
                },
                modifier = Modifier.fillMaxSize()
            )
        }

        // Flash Effect
        if (flashVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.8f))
            )
        }

        // Image Preview and Confirmation
        currentCapturedUri?.let { uri ->
            showPreview = true
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = "Captured Photo",
                modifier = Modifier.fillMaxSize()
            )
            Row(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        currentCapturedUri = null // Reset current image
                        showPreview = false // Return to camera preview
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Retake")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        savedImageUris.value.add(uri) // Add image to the list
                        currentCapturedUri = null // Reset current image
                        showPreview = false // Return to camera preview
                        onClose() // Exit the CameraWindow after confirmation
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Confirm")
                }
            }
        }

        if (!showPreview) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        cameraViewModel.takePhoto(
                            context = context,
                            onImageSaved = { uri ->
                                currentCapturedUri = uri
                                flashVisible = true
                                Handler(Looper.getMainLooper()).postDelayed({
                                    flashVisible = false
                                }, 200)
                            },
                            onError = { exception ->
                                Log.e("CameraWindow", "Error taking photo: ${exception.message}")
                            }
                        )
                    }
                ) {
                    Text("Tomar Foto")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { galleryLauncher.launch("image/*") } // Launch gallery picker
                ) {
                    Text("Importar de galeria")
                }
                Button(
                    onClick = { onClose() } // Launch gallery picker
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}

