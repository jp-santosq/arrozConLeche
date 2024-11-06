package com.example.awaq1.navigator
import UserSettingsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.awaq1.view.Home
import com.example.awaq1.view.Mapa
import com.example.awaq1.view.ObservationForm
import com.example.awaq1.view.ObservationListScreen
import com.example.awaq1.view.Settings
import com.example.awaq1.view.TwoFactor

@Composable
fun AppNavigator(onLogout: () -> Unit, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    //val startDestination = if (loggedIn) "home" else "log_in"
    val startDestination = "home"
    NavHost(navController = navController, startDestination = startDestination) {
//        composable("log_in") {
//            LogIn(navController = navController)
//        }
        composable("two_factor") {
            TwoFactor(navController = navController)
        }
        composable("mapa") {
            Mapa(navController = navController)
        }
        composable("settings") {
            // Logout is through the settings page
            Settings(navController = navController, onLogout)
        }
        composable("perfil") {
            UserSettingsScreen(navController = navController)
        }
        composable("reporte") {
            ObservationForm(navController = navController)
        }
        composable("buscar") {
            ObservationListScreen(navController = navController)
        }
        composable("home") {
            Home(navController = navController)
        }
    }
}