package com.example.awaq1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.awaq1.data.AppContainer
import com.example.awaq1.data.AppDataContainer
import com.example.awaq1.ui.theme.AWAQ1Theme
import com.example.awaq1.view.PrincipalView

class MainActivity : ComponentActivity() {
    lateinit var container: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        container = AppDataContainer(this)


        enableEdgeToEdge()
        setContent {
            AWAQ1Theme {
                PrincipalView()
                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AWAQ1Theme {
        Greeting("Android")
    }
}