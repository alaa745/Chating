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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.chatapp.domain.model.CountriesModelItem
import com.example.chatapp.presentation.splashScreen.WelcomeScreen
import com.example.chatapp.presentation.authScreen.MobileNumberScreen
import com.example.chatapp.ui.theme.ChatAppTheme
import com.example.todoapp.destination.MobileNumberScreen
import com.example.todoapp.destination.Welcome
import com.google.gson.Gson
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
        composable(
            route = "${MobileNumberScreen.route}/{countriesJson}",
            arguments = listOf(navArgument("countriesJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val countriesJson = backStackEntry.arguments?.getString("countriesJson")
            val countries: List<CountriesModelItem> = Gson().fromJson(countriesJson, Array<CountriesModelItem>::class.java).toList()
            MobileNumberScreen(navController, countries)
        }
    }
}