package com.example.awaq1.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationAPIClient
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.result.Credentials
import com.example.awaq1.R
import com.example.awaq1.navigator.AppNavigator

@Composable
fun PrincipalView(modifier: Modifier = Modifier, auth0: Auth0) {
    var loggedIn by remember { mutableStateOf(false) }
    var credentials by remember { mutableStateOf<Credentials?>(null) }

    Scaffold(modifier = modifier.fillMaxSize()) { innerPadding ->
        if (0==0) { // loggedIn o 0 == 0
            AppNavigator(
                onLogout = {
                    Log.d("AuthLogout", "Logging out!")
                    credentials = null
                    loggedIn = false
                },
                Modifier.padding(innerPadding))
        } else {
            LogIn(
                auth0 = auth0,
                onLoginSuccess = {
                    credentials = it
                    loggedIn = true
                },
                modifier = Modifier.padding(innerPadding)
            )
//            LoginScreen(
//                auth0 = auth0,
//                onLoginSuccess = {
//                    credentials = it
//                    loggedIn = true
//                },
//                modifier = Modifier.padding(innerPadding)
//            )
        }
    }
}

@Composable
fun LoginScreen(
    auth0: Auth0,
    onLoginSuccess: (Credentials) -> Unit,
    modifier: Modifier = Modifier
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) } // Variable de estado para el mensaje de error

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(stringResource(R.string.com_auth0_domain))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                loginWithUsernamePassword(auth0, username, password, onLoginSuccess, onError = { message ->
                    errorMessage = message // Actualiza el mensaje de error si ocurre un problema
                })
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }

        // Mostrar mensaje de error si existe
        errorMessage?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Error: $it", color = androidx.compose.ui.graphics.Color.Red)
        }
    }
}

fun loginWithUsernamePassword(
    auth0: Auth0,
    username: String,
    password: String,
    onSuccess: (Credentials) -> Unit,
    onError: (String) -> Unit
) {

    val authentication = AuthenticationAPIClient(auth0)
    authentication
        .login(username, password, "Username-Password-Authentication")
        .setConnection("Username-Password-Authentication")
        .validateClaims()
        .setScope("openid profile email")
        .start(object : Callback<Credentials, AuthenticationException> {
            override fun onSuccess(result: Credentials) {
                Log.d("AuthSuccess", "Autenticado como \"${username}\" exitosamente")
                onSuccess(result)
            }

            override fun onFailure(error: AuthenticationException) {
                // Imprime el error completo en los logs para ver más detalles
                Log.e("AuthError", "Error de autenticación: ${error.getDescription()}")
                // error.message no es muy descriptivo, nomas dice algo como
                // "Error authenticating with server" si falla cualquier cosa.
                // onError(error.message ?: "Error desconocido")
                onError(error.getDescription() ?: "Error desconocido")
            }
        })
}
