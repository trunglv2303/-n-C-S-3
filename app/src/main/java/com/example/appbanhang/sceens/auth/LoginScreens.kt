package com.example.appbanhang.sceens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.navigation.NavHostController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appbanhang.R
import com.example.appbanhang.sceens.Toast
import com.example.appbanhang.until.Response
import com.example.appbanhang.until.Screens

@Composable
fun LoginScreen(navController: NavHostController, viewModel: AuthViewModel) {
    Box(modifier = Modifier.fillMaxSize()
        ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment= Alignment.CenterHorizontally)
        {
            val emailState= remember{
                mutableStateOf("")
            }
            val passwordState= remember{
                mutableStateOf("")
            }
            Image(painter=painterResource(id= R.drawable.logo),
                contentDescription="Logo Screen",
                modifier= Modifier
                    .width(250.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp))
            Text(text="Sign In",
                modifier=Modifier
                    .padding(10.dp),
                fontSize=30.sp,
                fontFamily= FontFamily.SansSerif)
            OutlinedTextField(value=emailState.value,onValueChange={
                emailState.value=it
            },
                modifier=Modifier
                    .padding(10.dp),
                label={
                    Text(text="Vui Lòng Nhập Email")
                })
            OutlinedTextField(value=passwordState.value,onValueChange={
                passwordState.value=it
            },
                modifier=Modifier
                    .padding(10.dp),
                label={
                    Text(text="Vui Lòng Nhập Password")
                },
                visualTransformation= PasswordVisualTransformation()
            )
            Button(onClick={
                viewModel.signIn(
                    email=emailState.value,
                    password=passwordState.value
                )
            },
                modifier=Modifier
                    .padding(8.dp)
            )
            {
                Text(text="Sign In")
                when(val response = viewModel.signInState.value)
                {
                    is Response.Loading ->{
                    CircularProgressIndicator(
                        modifier=Modifier.fillMaxSize()
                    )
                    }
                    is Response.Success->{
                        if(response.data)
                        {
                            navController.navigate(Screens.FeedScreen.route){
                                popUpTo(Screens.LoginScreen.route)
                                {
                                    inclusive= true
                                }
                            }
                        }
                        else{
                            Toast(message="Đăng nhập thất bại")
                        }
                    }
                    is Response.Error->{
                        Toast(message=response.message)
                    }
                }

            }
            Text(text="Bạn chưa có tài khoản?",color= Color.Blue,
                modifier=Modifier
                    .padding(8.dp)
                    .clickable {
                        navController.navigate(route=Screens.SignUpScreen.route)
                        {
                            launchSingleTop =true
                        }
                    })
        }

    }
}
