import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.awaq1.MainActivity
import org.junit.Rule
import org.junit.Test

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