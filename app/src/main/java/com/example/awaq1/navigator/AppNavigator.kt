package com.example.awaq1.navigator
import UserSettingsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.awaq1.view.LogIn
import com.example.awaq1.view.Mapa
import com.example.awaq1.view.Settings
import com.example.awaq1.view.TwoFactor

@Composable
fun AppNavigator(modifier: Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "log_in") {
        composable("log_in") {
            LogIn(navController = navController)
        }
        composable("two_factor") {
            TwoFactor(navController = navController)
        }
        composable("mapa") {
           Mapa(navController = navController)
        }
        composable("settings") {
            Settings(navController = navController)
        }
        composable("perfil") {
            UserSettingsScreen(navController = navController)
        }
    }
}