package com.example.chatapp.presentation.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.chatapp.R
import com.example.todoapp.destination.MobileNumberScreen
import com.example.todoapp.destination.Welcome
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun WelcomeScreen(navController: NavHostController , splashScreenViewModel: SplashScreenViewModel = hiltViewModel()) {
    val isLoading = splashScreenViewModel.isLoading.observeAsState()
    val message by splashScreenViewModel.message.collectAsState()
    val countries by splashScreenViewModel.countries.collectAsState()
    if (isLoading.value == false){
        if (countries.isNotEmpty()){
            val countriesJson = Gson().toJson(countries)
            val encodedCountriesJson = URLEncoder.encode(countriesJson, StandardCharsets.UTF_8.toString())
            navController.navigate("${MobileNumberScreen.route}/$encodedCountriesJson")

        }
    }
//        LaunchedEffect(isLoading.value) {
////        delay(500)
//                navController.navigate(MobileNumberScreen.route) {
//                    popUpTo(Welcome.route) {
//                        inclusive = true
//                    }
//                }
//
//
//        }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(modifier = Modifier.padding(top = 100.dp)) {
            Image(
                painter = painterResource(id = R.drawable.welcome),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.size(width = 262.dp, height = 271.dp)
            )
        }
        Text(
            text = "Connect easily with \nyour family and friends over countries",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F1828)
        )
        Box(modifier = Modifier.padding(top = 100.dp)) {
            Button(
                onClick = {
                    navController.navigate(MobileNumberScreen.route) {
                        popUpTo(Welcome.route)
                    }
                },
                modifier = Modifier.size(width = 327.dp, height = 52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF002DE3))
            ) {
                Text(text = "Start Messaging")
            }
        }
        if (isLoading.value!!) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.6f)),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
    }
}