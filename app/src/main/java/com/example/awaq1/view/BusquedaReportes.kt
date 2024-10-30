package com.example.awaq1.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.awaq1.MainActivity
import com.example.awaq1.R
import com.example.awaq1.data.FormularioUnoEntity
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ObservationListScreen(navController: NavController) {
    val context = LocalContext.current as MainActivity
    val appContainer = context.container
    val formsList: List<FormularioUnoEntity> by appContainer.formularioUnoRepository.getAllFormularioUnosStream()
        .collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Observaciones") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(
                            painter = painterResource(id = R.drawable.leading_iconarrow),
                            contentDescription = "Atrás"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxSize()
                ) {
                    items(formsList) { form ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable {
                                   // navController.navigate("formDetail/${formsList.}")
                                },
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(text = "Transecto: ${form.transecto}", style = MaterialTheme.typography.headlineSmall)
                                Text(text = "Tipo de Animal: ${form.tipoAnimal}", style = MaterialTheme.typography.bodyMedium)
                                Text(text = "Nombre Común: ${form.nombreComun}", style = MaterialTheme.typography.bodyMedium)
                                Text(text = "Observaciones: ${form.observaciones}", style = MaterialTheme.typography.bodyMedium)
                                // Add more fields if desired
                            }
                        }
                    }
                }

            }
        }
    )
}
