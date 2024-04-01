package com.example.appbanhang.until

sealed class Screens(val route:String)
{
    //route định danh cho màn hình chờ
    object SplashScreen:Screens("splash_screen")
    //route định danh cho màn hình đăng nhập
    object LoginScreen:Screens("login_screen")
    //route định danh cho màn hình đăng kí
    object SignUpScreen:Screens("signup_screen")
    //route định danh cho màn hình trang chủ
    object FeedScreen:Screens("feed_screen")

}