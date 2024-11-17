package com.example.awaq1

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.auth0.android.Auth0
import com.example.awaq1.data.AccountInfo
import com.example.awaq1.data.AppContainer
import com.example.awaq1.data.AppDataContainer
import com.example.awaq1.ui.theme.AWAQ1Theme
import com.example.awaq1.view.PrincipalView


class MainActivity : ComponentActivity() {
    lateinit var container: AppContainer
    private lateinit var account: Auth0
    lateinit var accountInfo: AccountInfo

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        container = AppDataContainer(this)

        account = Auth0.getInstance(
            getString(R.string.com_auth0_client_id),
            getString(R.string.com_auth0_domain)
        )
        if (!arePermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this, CAMERA_PERMISSION, 100
            )
        }

        enableEdgeToEdge()
        setContent {
            AWAQ1Theme {
                PrincipalView(modifier = Modifier, account)
            }
        }
    }

    fun arePermissionsGranted(): Boolean {
        return CAMERA_PERMISSION.all { permission ->
            ContextCompat.checkSelfPermission(
                applicationContext,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object {
        val CAMERA_PERMISSION = arrayOf(
            Manifest.permission.CAMERA
        )
    }
}


