package com.example.awaq1.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.awaq1.MainActivity
import com.example.awaq1.data.formularios.FormularioCuatroEntity
import com.example.awaq1.data.formularios.FormularioDosEntity
import com.example.awaq1.data.formularios.FormularioTresEntity
import com.example.awaq1.data.formularios.FormularioUnoEntity
import com.example.awaq1.navigator.FormCuatroID
import com.example.awaq1.navigator.FormDosID
import com.example.awaq1.navigator.FormTresID
import com.example.awaq1.navigator.FormUnoID

@Composable
fun Home(navController: NavController) {
    val context = LocalContext.current as MainActivity
    val appContainer = context.container
    val forms1: List<FormularioUnoEntity> by appContainer.usuariosRepository.getAllFormularioUnoForUserID(
        context.accountInfo.user_id
    )
        .collectAsState(initial = emptyList())
    val forms2: List<FormularioDosEntity> by appContainer.usuariosRepository.getAllFormularioDosForUserID(
        context.accountInfo.user_id
    )
        .collectAsState(initial = emptyList())
    val forms3: List<FormularioTresEntity> by appContainer.usuariosRepository.getAllFormularioTresForUserID(
        context.accountInfo.user_id
    )
        .collectAsState(initial = emptyList())
    val forms4: List<FormularioCuatroEntity> by appContainer.usuariosRepository.getAllFormularioCuatroForUserID(
        context.accountInfo.user_id
    )
        .collectAsState(initial = emptyList())
    val count by appContainer.formulariosRepository.getFormularioUnoCount()
        .collectAsState(initial = 0)

    Scaffold(
        bottomBar = {
            Column() {
                BottomNavigationBar(navController)
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFFFF))
                    .padding(paddingValues)
            ) {
                // Header
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .clip(RoundedCornerShape(bottomStart = 2000.dp, bottomEnd = 2000.dp))
                        .background(Color(0xFFCDE4B4)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Hola, Samantha",
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4E7029)
                    )
                }

                // Dashboard Section
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Dashboard",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF333333),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Stats Row
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        StatsColumn(label = "Total", count = count, color = Color.Black)
                        StatsColumn(label = "Enviados", count = 3, color = Color(0xFF4CAF50))
                        StatsColumn(label = "Guardados", count = 2, color = Color.Red)
                    }

                    // Forms Grid
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .padding(horizontal = 0.dp, vertical = 8.dp)
                            .fillMaxWidth()
                    ) {
                        items(forms1) { form ->
                            val formCard = FormInfo(form)
                            formCard.DisplayCard(navController)
                        }
                        items(forms2) { form ->
                            val formCard = FormInfo(form)
                            formCard.DisplayCard(navController)
                        }
                        items(forms3) { form ->
                            val formCard = FormInfo(form)
                            formCard.DisplayCard(navController)
                        }
                        items(forms4) { form ->
                            val formCard = FormInfo(form)
                            formCard.DisplayCard(navController)
                        }
                        items(1) {
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun StatsColumn(label: String, count: Int, color: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "$count",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            fontSize = 24.sp,
            color = Color.Gray
        )
    }
}

// Se ve algo así
// +---------------+
// | tipo:valorId  |
// | pTag: pCont   |
// | sTag: sCont   |
// +---------------+

data class FormInfo(
    val tipo: String, // Descripcion del tipo de formulario (una sola palabra)
    val valorIdentificador: String, // Valor que se muestra junto tipo
    val primerTag: String, // Tag del primer valor a mostrar como preview del formulario
    val primerContenido: String, // El valor a mostrar junto al primer tag
    val segundoTag: String,
    val segundoContenido: String,

    val formulario: String, // Indicador de tipo de formulario, para luego acceder
    val formId: Long
) {
    constructor(formulario: FormularioUnoEntity) : this(
        tipo = "Transecto", formulario.transecto,
        primerTag = "Tipo", formulario.tipoAnimal,
        segundoTag = "Nombre", formulario.nombreComun,
        formulario = "form1",
        formId = formulario.id
    )

    constructor(formulario: FormularioDosEntity) : this(
        tipo = "Zona", formulario.zona,
        primerTag = "Tipo", formulario.tipoAnimal,
        segundoTag = "Nombre", formulario.nombreComun,
        formulario = "form2",
        formId = formulario.id
    )

    constructor(formulario: FormularioTresEntity) : this(
        tipo = "Código", formulario.codigo,
        primerTag = "Seguimiento", siONo(formulario.seguimiento),
        segundoTag = "Cambio", siONo(formulario.cambio),
        formulario = "form3",
        formId = formulario.id
    )

    constructor(formulario: FormularioCuatroEntity) : this(
        tipo = "Código", formulario.codigo,
        primerTag = "Cuad. A", formulario.quad_a,
        segundoTag = "Cuad. B", formulario.quad_b,
        formulario = "form4",
        formId = formulario.id
    )

    fun goEditFormulario(navController: NavController) {
        Log.d("HOME_CLICK_ACTION", "Click en $this")
        when (formulario) {
            "form1" -> navController.navigate(route = FormUnoID(formId))
            "form2" -> navController.navigate(route = FormDosID(formId))
            "form3" -> navController.navigate(route = FormTresID(formId))
            "form4" -> navController.navigate(route = FormCuatroID(formId))
            else -> throw Exception("CARD NAVIGATION NOT IMPLEMENTED FOR $formulario")
        }
    }

    @Composable
    fun DisplayCard(navController: NavController, modifier: Modifier = Modifier) {
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding()
                .clickable { this.goEditFormulario(navController) },
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
        ) {
            Row(
                modifier = Modifier.padding(20.dp).fillMaxWidth().padding(end = 40.dp),
                Arrangement.SpaceBetween

            ) {
                Text(
                    text = "$tipo: $valorIdentificador",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Column {
                    Text(
                        text = "$primerTag: $primerContenido",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )

                    Text(
                        text = "$segundoTag: $segundoContenido",
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black
                    )
                }
            }
        }
    }
}

private fun siONo(boolean: Boolean): String = if (boolean) "Sí" else "No"