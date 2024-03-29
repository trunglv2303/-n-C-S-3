package com.example.appbanhang.until

sealed class Response<out T>
{
    object Loading: Response<Nothing>()
    // hàm trả về OK
    data class Success<out T>(
        val data:T
    ):Response<T>()
    //hàm trả về KO
    data class Error(
        val message:String
    ):Response<Nothing>()
}