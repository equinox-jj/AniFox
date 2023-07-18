package com.anifox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.core.ui.theme.AniFoxTheme
import com.core.util.Routes.ANIME_SCREEN

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniFoxTheme {
                val navController = rememberNavController()

                MainScreen(navController = navController, startDestination = ANIME_SCREEN)
            }
        }
    }
}
