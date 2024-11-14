package com.example.awaq1

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.awaq1.MainActivity
import org.junit.Rule
import org.junit.Test

class SettingsTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()
    @Test
    fun testSettingsScreen() {
        // Navegar a la pantalla de Configuración
        composeTestRule.onNodeWithContentDescription("settings").performClick()
        // Esperar a que la UI esté lista
        composeTestRule.runOnIdle { }
        // Verificar que la pantalla de Configuración esté visible
        composeTestRule.onNodeWithText("Configuración").assertIsDisplayed()

        // Buscar y hacer clic en "Editar perfiles"
        composeTestRule.onNodeWithText("Editar perfiles").performClick()
        // Esperar a que la UI esté lista
        composeTestRule.runOnIdle { }
        // Verificar que se navegue a "UserSettingsScreen"
        composeTestRule.onNodeWithText("perfil").assertIsDisplayed()
    }

}