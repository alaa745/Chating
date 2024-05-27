package com.example.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chatapp.presentation.authScreen.MobileNumberScreen
import com.example.chatapp.presentation.WelcomeScreen
import com.example.chatapp.ui.theme.ChatAppTheme
import com.example.todoapp.destination.MobileNumberScreen
import com.example.todoapp.destination.Welcome
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppTheme {
                Surface(modifier = Modifier.fillMaxSize() , color = MaterialTheme.colorScheme.background) {
                    MyNavigation()
                }
            }
        }
    }
}

@Composable
fun MyNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Welcome.route) {
        composable(Welcome.route){
            WelcomeScreen(navController)
        }
        composable(MobileNumberScreen.route){
            MobileNumberScreen(navController)
        }
    }
}