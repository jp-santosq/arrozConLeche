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
import androidx.compose.material3.Button
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
    savedImageUri: MutableState<Uri?>,
    onClose: () -> Unit,
    onGalleryClick: () -> Unit
) {
    var flashVisible by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            if (uri != null) {
                savedImageUri.value = uri // Save the selected image URI
            }
        }
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // Camera Preview
        AndroidView(
            factory = { context ->
                PreviewView(context).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    cameraViewModel.bindCamera(this, activity)
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // Flash Effect
        if (flashVisible) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = 0.8f))
            )
        }

        // Confirmation UI
        savedImageUri.value?.let { uri ->
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
                        savedImageUri.value = null // Retake photo
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Retake")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        onClose()
                        cameraViewModel.savePhotoUri(uri)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Confirm")
                }
            }
        }

        if (savedImageUri.value == null) {
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
                                savedImageUri.value = uri
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
                    Text("Take Photo")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { galleryLauncher.launch("image/*") } // Launch gallery picker
                ) {
                    Text("Import from Gallery")
                }
            }
        }
    }
}
