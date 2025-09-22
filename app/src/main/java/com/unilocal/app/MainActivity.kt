package com.unilocal.app

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
import com.unilocal.app.ui.screens.LoginScreen
import com.unilocal.app.ui.theme.UniLocalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Aquí sí puedes llamar composables
            LoginScreen(
                onNavigateToRegister = {
                    // Navegación a registro
                },
                onNavigateToHome = {
                    // Navegación a home
                }
            )
        }
    }
}
