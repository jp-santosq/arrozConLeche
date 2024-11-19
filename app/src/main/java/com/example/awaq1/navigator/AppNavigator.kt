package com.example.awaq1.navigator
import UserSettingsScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.awaq1.view.Home
import com.example.awaq1.view.Mapa
import com.example.awaq1.view.ObservationForm
import com.example.awaq1.view.ObservationFormDos
import com.example.awaq1.view.ObservationFormCuatro
import com.example.awaq1.view.ObservationFormTres
import com.example.awaq1.view.ObservationListScreen
import com.example.awaq1.view.SelectFormularioScreen
import com.example.awaq1.view.Settings
import com.example.awaq1.view.TwoFactor
import kotlinx.serialization.Serializable

@Serializable
data class FormUnoID(val form_id: Long = 0)

@RequiresApi(Build.VERSION_CODES.P)
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

        composable("elegir_reporte") {
            SelectFormularioScreen(navController = navController)
        }

        composable <FormUnoID> { backStackEntry ->
            val formId: FormUnoID = backStackEntry.toRoute()
            ObservationForm(navController = navController, formId.form_id)
        }
        composable("reporte_2") {
            ObservationFormDos(navController = navController)
        }
        composable("reporte_3") {
           ObservationFormTres(navController = navController)
        }
        composable("reporte_4") {
            ObservationFormCuatro(navController = navController)
        }

        composable("buscar") {
            ObservationListScreen(navController = navController)
        }
        composable("home") {
            Home(navController = navController)
        }
    }
}
