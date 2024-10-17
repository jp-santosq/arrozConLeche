package com.example.awaq1.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.awaq1.R


@Composable
fun LogIn(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize())
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            //Bienvenido
            Text(
                text = stringResource(R.string.bienvenido),
                fontSize = 37.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 110.dp)
            )
            //Inicia Sesion
            Text(text = stringResource(R.string.inicia_sesion),
                fontSize = 30.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 150.dp)
            )
            //Email
            TextField(value = stringResource(R.string.email), onValueChange ={},
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            )
            //Contraseñq
            TextField(value = stringResource(R.string.password), onValueChange ={},
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
            )
            //Olvidaste Contraseña
            Text(text = stringResource(R.string.forgot_password),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 5.dp),
                color = Color(0xFF4E7029),
                fontWeight = FontWeight.Bold,
            )
            //Entrar
            Button(onClick = {
                navController.navigate("two_factor")
            },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 170.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF4E7029), // Example: Dodger Blue
                    contentColor = Color(0xFFFFFFFF) // Example: White text
                )
            ) {
                Text(
                    text = stringResource(R.string.enter),
                    fontSize = 20.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }

}