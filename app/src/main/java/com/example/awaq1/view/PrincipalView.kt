package com.example.awaq1.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.awaq1.navigator.AppNavigator

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PrincipalView(modifier: Modifier = Modifier) {
    Scaffold(
        content = { innerPadding ->
            AppNavigator(Modifier.padding(innerPadding))
        }
    )
}

