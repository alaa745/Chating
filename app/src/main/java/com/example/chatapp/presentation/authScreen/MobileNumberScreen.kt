package com.example.chatapp.presentation.authScreen

import android.annotation.SuppressLint
import android.app.Activity
import android.provider.CalendarContract.Colors
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.model.GlideUrl
import com.example.chatapp.domain.model.CountriesModelItem
import com.example.chatapp.presentation.splashScreen.SplashScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Locale

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileNumberScreen(navController: NavHostController , countriesList: List<CountriesModelItem> , authViewModel: AuthViewModel = hiltViewModel()) {
    val scaffoldState = rememberStandardBottomSheetState(
        initialValue = SheetValue.Hidden,
        skipHiddenState = false
    )
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(scaffoldState)
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        bottomSheetScaffoldState.bottomSheetState.hide()
    }
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 50.dp,
        sheetContent = {

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(
                    text = "Add New Task",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )

            }
        }
    ){
        AuthScreenContent(authViewModel = authViewModel , coroutineScope = coroutineScope , bottomSheetScaffoldState = bottomSheetScaffoldState , countriesList = countriesList)
    }

}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)
@Composable
fun AuthScreenContent(authViewModel: AuthViewModel , coroutineScope: CoroutineScope , bottomSheetScaffoldState: BottomSheetScaffoldState , countriesList: List<CountriesModelItem>){
    val scrollableState = rememberScrollState()
    val keyboardController = LocalSoftwareKeyboardController.current
    val density = LocalDensity.current
    val phoneNumber = rememberSaveable {
        mutableStateOf("")
    }
    val isLoading by authViewModel.isLoading.collectAsState()
    val message by authViewModel.message.collectAsState()
    val isCodeSent by authViewModel.isCodeSent.collectAsState()


    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .verticalScroll(state = scrollableState)
                .fillMaxSize()
//                .padding(start = 40.dp, end = 40.dp),
        ) {
            Spacer(modifier = Modifier.height(150.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Enter Your Phone Number",
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F1828)
                )
                Text(
                    text = "Please confirm your country code and enter your phone number",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF0F1828)
                )
            }
            Spacer(modifier = Modifier.height(50.dp))

            Row(verticalAlignment = Alignment.CenterVertically , modifier = Modifier.padding(start = 20.dp , end = 20.dp)) {
                Card(
                    colors = CardDefaults.cardColors()
                    , shape = RoundedCornerShape(5.dp)
                    , modifier = Modifier.fillMaxHeight()
                    , onClick = {

                }) {
                    Row (verticalAlignment = Alignment.CenterVertically , modifier = Modifier.padding(start = 5.dp , end = 10.dp , top = 5.dp , bottom = 5.dp)){
                        AsyncImage(
                            model = countriesList.find {
                                it.name.lowercase() == "egypt"
                            }?.flag,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(40.dp).clip(RoundedCornerShape(15.dp))
                        )
                        countriesList.find {
                            it.name.lowercase() == "egypt"
                        }?.dialCode?.let {
                            Text(text = it, modifier = Modifier.padding(start = 10.dp) , fontWeight = FontWeight.Bold) }
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))

                TextField(
                    value = phoneNumber.value,
                    onValueChange = {
                        phoneNumber.value = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    placeholder = {
                        Text(text = "Phone Number")
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )
            }

        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        ) {
            Button(
                onClick = {
                    authViewModel.sendVerificationCode(phoneNumber = phoneNumber.value , activity = context as Activity)
                },
                modifier = Modifier.size(width = 327.dp, height = 52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF002DE3))
            ) {
                Text(text = "Send Code", fontWeight = FontWeight.Medium, fontSize = 17.sp)
            }
        }
        if (isCodeSent)
            Toast.makeText(context , message , Toast.LENGTH_LONG).show()
    }
}

@Composable
fun BottomSheetContent(){

}


