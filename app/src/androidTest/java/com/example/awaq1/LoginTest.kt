import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.awaq1.MainActivity
import org.junit.Rule
import org.junit.Test

// Requerimientos

// Para ejecutar la prueba se debe de desabilitar el Login de la aplicación
// Se desactiva al cambiar la linea 39 del archivo PrincipalView.kt
// Se cambia de if (loggedIn) { a if (0 == 0) {

// Descripcion

// La prueba BottomNavigationBarTest corre tres diferentes pruebas en donde
// intenta navegar en la aplicacion desde la Home page. Las pruebas checan que
// se pueda mover de home a los formularios y a los ajustes.

class BottomNavigationBarTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testHomeButtonNavigatesToHome() {
        // Simulate navigating to the Home screen
        composeTestRule.onNodeWithContentDescription("Home").performClick()

        // Wait for the UI to settle
        composeTestRule.runOnIdle { }

        // Verify that the displayed screen is Home
        composeTestRule.onNodeWithText("Hola, Samantha").assertIsDisplayed() // For Home screen    }
    }

    @Test
    fun testReporteButtonNavigatesToReporte() {
        // Simulate navigating to the Reporte screen
        composeTestRule.onNodeWithContentDescription("Reporte").performClick()

        // Wait for the UI to settle
        composeTestRule.runOnIdle { }

        // Verify that the displayed screen is Reporte
        composeTestRule.onNodeWithText("Formulario de Observación").assertIsDisplayed() // For Reporte screen
    }

    @Test
    fun testSettingsButtonNavigatesToSettings() {
        // Simulate navigating to the Settings screen
        composeTestRule.onNodeWithContentDescription("Settings").performClick()

        // Wait for the UI to settle
        composeTestRule.runOnIdle { }

        // Verify that the displayed screen is Settings
        composeTestRule.onNodeWithText("Configuración").assertIsDisplayed() // For Settings screen
    }
}