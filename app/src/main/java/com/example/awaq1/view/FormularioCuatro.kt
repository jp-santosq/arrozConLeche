package com.example.awaq1.view

import androidx.compose.foundation.Image
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
import com.example.awaq1.data.formularioUno.FormularioUnoEntity
import kotlinx.coroutines.runBlocking
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.filled.Add
import androidx.compose.ui.draw.scale
import com.example.awaq1.ViewModels.CameraViewModel
import com.example.awaq1.data.formularioUno.FormularioCuatroEntity
import com.example.awaq1.data.formularioUno.FormularioDosEntity
import com.example.awaq1.data.formularioUno.FormularioTresEntity
import com.example.awaq1.view.CameraView


@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewForm4() {
    ObservationFormCuatro(rememberNavController())
}

@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ObservationFormCuatro(navController: NavController) {
    val context = LocalContext.current as MainActivity
    val appContainer = context.container

    var codigo: String by remember { mutableStateOf("") }
    var quad_a: String by remember { mutableStateOf("") }
    var quad_b: String by remember { mutableStateOf("") }
    var sub_quad: String by remember { mutableStateOf("") }
    var habitoDeCrecimiento: String by remember { mutableStateOf("") }
    var nombreComun: String by remember { mutableStateOf("") }
    var nombreCientifico: String by remember { mutableStateOf("") }
    var placa: String by remember { mutableStateOf("") }
    var circunferencia: String by remember { mutableStateOf("") }
    var distancia: String by remember { mutableStateOf("") }
    var estatura: String by remember { mutableStateOf("") }
    var altura: String by remember { mutableStateOf("") }
    var observaciones: String by remember { mutableStateOf("") }
    var showCamera by remember { mutableStateOf(false) }

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
                    cameraViewModel = CameraViewModel(),
                    onClose = { showCamera = false }
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
                        OutlinedTextField(
                            value = codigo,
                            onValueChange = { codigo = it },
                            label = { Text("Código") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Text("Cuadrante")
                        val quadAOpciones = listOf("A", "B")
                        val quadBOpciones = listOf("C", "D", "E", "F", "G")
                        if (quad_a == "") {
                            quad_a = quadAOpciones[0]
                        }
                        if (quad_b == "") {
                            quad_b = quadBOpciones[0]
                        }
                        Row(modifier = Modifier) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                quadAOpciones.forEach { opcion_QA ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.scale(2f)
                                    ) {
                                        RadioButton(
                                            selected = quad_a == opcion_QA,
                                            onClick = { quad_a = opcion_QA },
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = Color(0xFF4E7029),
                                                unselectedColor = Color.Gray
                                            )
                                        )
                                        Text(
                                            opcion_QA,
                                            style = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        )
                                    }
                                }
                            }
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) {
                                quadBOpciones.forEach { opcion_QB ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.scale(2f)
                                    ) {
                                        RadioButton(
                                            selected = quad_b == opcion_QB,
                                            onClick = { quad_b = opcion_QB },
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = Color(0xFF4E7029),
                                                unselectedColor = Color.Gray
                                            )
                                        )
                                        Text(
                                            opcion_QB,
                                            style = TextStyle(
                                                fontSize = 20.sp,
                                                fontWeight = FontWeight.SemiBold
                                            )
                                        )
                                    }
                                }
                            }
                        }

                        Text("Sub-Cuadrante")
                        val subQuadOpciones = listOf("1", "2", "3", "4")
                        if (sub_quad == "") {
                            sub_quad = subQuadOpciones[0]
                        }

                        FlowRow(modifier = Modifier.fillMaxWidth()) {
                            subQuadOpciones.forEach { opcion_sub ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                ) {
                                    RadioButton(
                                        selected = sub_quad == opcion_sub,
                                        onClick = { sub_quad = opcion_sub },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color(0xFF4E7029),
                                            unselectedColor = Color.Gray
                                        )
                                    )
                                    Text(
                                        opcion_sub,
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.SemiBold
                                        )
                                    )
                                }
                            }
                        }

			Text("Hábito de Crecimiento")
                        val alturaOptions: List<Pair<String, String>> = listOf(Pair("Arbusto", "<1mt"), Pair("Arbolito", "1-3mt"), Pair("Árbol", ">3mt"))
                        if (habitoDeCrecimiento == "") {
                            habitoDeCrecimiento = alturaOptions[0].first
                        }
                        Column {
                            alturaOptions.forEach { option ->
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    RadioButton(
                                        selected = habitoDeCrecimiento == option.first,
                                        onClick = { habitoDeCrecimiento = option.first },
                                        colors = RadioButtonDefaults.colors(
                                            selectedColor = Color(0xFF4E7029),
                                            unselectedColor = Color.Gray
                                        )
                                    )
                                    Text("${option.first} ${option.second}", modifier = Modifier.padding(start = 8.dp))
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
                            value = placa,
                            onValueChange = { placa = it },
                            label = { Text("Placa") },
                            modifier = Modifier.fillMaxWidth()
                        )

			OutlinedTextField(
                            value = circunferencia,
                            onValueChange = { circunferencia = it },
                            label = { Text("Circunferencia en cm") },
                            modifier = Modifier.fillMaxWidth()
                        )

			OutlinedTextField(
                            value = distancia,
                            onValueChange = { distancia = it },
                            label = { Text("Distancia en mt") },
                            modifier = Modifier.fillMaxWidth()
                        )

			OutlinedTextField(
                            value = estatura,
                            onValueChange = { estatura = it },
                            label = { Text("Estatura Biomonitor en mt") },
                            modifier = Modifier.fillMaxWidth()
                        )

			OutlinedTextField(
                            value = altura,
                            onValueChange = { altura = it },
                            label = { Text("Altura en mt") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        // Botón de cámara actualizado
                        Button(
                            onClick = {
                                if (context.arePermissionsGranted()) {
                                    showCamera = true
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4E7029),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(10)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Tomar foto",
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Tomar Foto")
                        }

                        // Observaciones
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
                                    val formulario = FormularioCuatroEntity(
                                        codigo = codigo,
                                        quad_a = quad_a,
                                        quad_b = quad_b,
                                        sub_quad = sub_quad,
                                        habitoDeCrecimiento = habitoDeCrecimiento,
                                        nombreComun = nombreComun,
                                        nombreCientifico = nombreCientifico,
                                        placa = placa,
                                        circunferencia = circunferencia,
                                        distancia = distancia,
                                        estatura = estatura,
                                        altura = altura,
                                        observaciones = observaciones
                                    )
                                    runBlocking {
                                        appContainer.formularioUnoRepository.insertFormularioCuatro(formulario)
                                        Log.d("FORM", formulario.toString())
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
