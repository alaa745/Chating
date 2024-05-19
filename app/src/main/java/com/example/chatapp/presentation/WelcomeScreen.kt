package com.example.chatapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.chatapp.R
import com.example.todoapp.destination.MobileNumberScreen
import com.example.todoapp.destination.Welcome

@Composable
fun WelcomeScreen(navController: NavHostController) {
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
        Text(text = "Connect easily with \nyour family and friends over countries" , fontSize = 24.sp , textAlign = TextAlign.Center , fontWeight = FontWeight.Bold , color = Color(0xFF0F1828))
        Box(modifier = Modifier.padding(top = 100.dp)) {
            Button(
                onClick = {
                          navController.navigate(MobileNumberScreen.route){
                              popUpTo(Welcome.route)
                          }
                },
                modifier = Modifier.size(width = 327.dp, height = 52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF002DE3))
            ) {
                Text(text = "Start Messaging")
            }
        }
    }
}