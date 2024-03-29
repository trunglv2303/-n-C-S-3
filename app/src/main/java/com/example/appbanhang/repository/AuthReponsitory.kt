package com.example.appbanhang.repository

import com.example.appbanhang.until.Response
import kotlinx.coroutines.flow.Flow

interface AuthReponsitory {
    //Kiểm tra xem người dùng hiện có được xác thực bằng Firebase hay không.
    fun isUserAuthFireBase() : Boolean
    //Theo dõi trạng thái đăng nhập của người dùng.
    fun getFirebaseAuthState(): Flow<Boolean>
    //Đăng nhập vào Firebase và trả về Luồng có phản hồi (thành công/thất bại).
    fun firebaseSignIn(email:String,password:String) : Flow<Response<Boolean>>
    //Bắt đầu đăng xuất Firebase và trả về một Luồng có phản hồi.
    fun firebaseSignOut():Flow<Response<Boolean>>
    //Tạo tài khoản người dùng Firebase mới và trả về Luồng có phản hồi.
    fun firebaseSignUp(email:String,password:String,userName:String,name:String):Flow<Response<Boolean>>

}