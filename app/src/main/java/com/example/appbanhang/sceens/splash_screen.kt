package com.example.appbanhang.sceens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.appbanhang.R
import androidx.navigation.NavController
import com.example.appbanhang.sceens.auth.AuthViewModel
import com.example.appbanhang.until.Screens
import kotlinx.coroutines.delay

@Composable

fun SplashScreen(navController: NavController, authViewModel: AuthViewModel) {
    val authValue = authViewModel.isUserAuth
    val scale = remember{
        Animatable(0f)
    }
    LaunchedEffect(key1=true){
        scale.animateTo(targetValue=0.5f,animationSpec= tween(durationMillis=1500,easing={
            OvershootInterpolator(2f).getInterpolation(it)
        })
        )
        delay(3000)
        if(authValue){
            navController.navigate(Screens.FeedScreen.route)
            {
                popUpTo(Screens.SplashScreen.route)
                {
                    inclusive= true
                }
            }
        }
        else{
            navController.navigate(Screens.LoginScreen.route)
            {
                popUpTo(Screens.SplashScreen.route)
                {
                    inclusive= true
                }
            }
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.background(color = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(5f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier=Modifier
                    .size(1000.dp)
            )
        }
        Box(
            modifier = Modifier.fillMaxWidth().weight(2f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Image(
                painter = painterResource(id = R.drawable.with),
                contentDescription = "With",
                modifier=Modifier
                    .size(2000.dp)

            )
        }
    }
}