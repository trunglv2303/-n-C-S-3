package com.example.appbanhang.sceens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.appbanhang.R
import com.example.appbanhang.sceens.Toast
import com.example.appbanhang.until.Response
import com.example.appbanhang.until.Screens

@Composable
fun SignUpScreen(navController:NavHostController,viewModel: AuthViewModel)
{
    Box(modifier= Modifier.fillMaxSize())
    {
        Column (modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally){
            val emailState= remember{
                mutableStateOf("")
            }
            val passwordState= remember{
                mutableStateOf("")
            }
            val replayPasswordState=remember{
                mutableStateOf("")
            }
            val nameState = remember {
                mutableStateOf("")
            }
            val userNameState=remember{
                mutableStateOf("")
            }
            Image(painter= painterResource(id= R.drawable.logo),
                contentDescription="Logo Screen",
                modifier= Modifier
                    .width(250.dp)
                    .padding(top = 16.dp)
                    .padding(8.dp))
            Text(text="Sign Up",
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
            OutlinedTextField(value=passwordState.value,onValueChange={
                passwordState.value=it
            },
                modifier=Modifier
                    .padding(10.dp),
                label={
                    Text(text="Vui Lòng Nhập Lại Password")
                },
                visualTransformation= PasswordVisualTransformation()
            )
            OutlinedTextField(value=nameState.value,onValueChange={
                nameState.value=it
            },
                modifier=Modifier
                    .padding(10.dp),
                label={
                    Text(text="Vui Lòng Nhập Tên Của Bạn")
                })
            OutlinedTextField(value=userNameState.value,onValueChange={
                userNameState.value=it
            },
                modifier=Modifier
                    .padding(10.dp),
                label={
                    Text(text="Vui Lòng Nhập Tên Đăng Nhập ")
                },
                visualTransformation= PasswordVisualTransformation()
            )
            Button(onClick={
                viewModel.signUp(
                    email=emailState.value,
                    password=passwordState.value,
                    name=nameState.value,
                    userName = userNameState.value
                )
            },
                modifier=Modifier
                    .padding(8.dp)
            )
            {
                Text(text="Login Up")
                when(val response = viewModel.signUpState.value)
                {
                    is Response.Loading->{
                        CircularProgressIndicator(
                            modifier=Modifier.fillMaxSize()
                        )
                    }
                    is Response.Success->{
                        if(emailState!=null)
                        {
                            Toast(message = "Email đã tồn tại!")
                        }
                        else if(passwordState!=replayPasswordState)
                        {
                            Toast(message="Nhập lại mật khẩu bị sai!")
                        }
                        else if(response.data){
                            navController.navigate(Screens.FeedScreen.route){
                                popUpTo(Screens.LoginScreen.route)
                                {
                                    inclusive= true
                                }
                            }
                        }
                    }
                    is Response.Error->{
                        Toast(message = response.message)
                    }
                }
                Text(text="Tôi đã có tài khoản",color= Color.Blue,
                    modifier=Modifier
                        .padding(8.dp)
                        .clickable {
                            navController.navigate(route= Screens.LoginScreen.route)
                            {
                                launchSingleTop =true
                            }
                        })
            }
        }
    }
}
