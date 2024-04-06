package com.example.appbanhang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appbanhang.sceens.SplashScreen
import com.example.appbanhang.sceens.auth.AuthViewModel
import com.example.appbanhang.sceens.auth.LoginScreen
import com.example.appbanhang.sceens.auth.SignUpScreen
import com.example.appbanhang.sceens.main.FeedScreens
import com.example.appbanhang.ui.theme.AppBanHangTheme
import com.example.appbanhang.until.Screens
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppBanHangTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val authViewModel: AuthViewModel = viewModel()
                    AppShopping(navController,authViewModel)
                }
            }
        }
    }
}

@Composable
fun AppShopping(navController: NavHostController,authViewModel:AuthViewModel) {
    NavHost(navController = navController, startDestination = Screens.SplashScreen.route) {
        composable(route=Screens.LoginScreen.route)
        {
            LoginScreen(navController=navController,authViewModel)
        }
        composable(route=Screens.SignUpScreen.route)
        {
            SignUpScreen(navController=navController,authViewModel)
        }
        composable(route=Screens.SplashScreen.route)
        {
            SplashScreen(navController = navController, authViewModel = authViewModel)
        }
        composable(route=Screens.FeedScreen.route)
        {
            FeedScreens()
        }
    }
}

