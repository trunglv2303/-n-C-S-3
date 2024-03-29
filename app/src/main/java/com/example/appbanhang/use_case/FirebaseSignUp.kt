package com.example.appbanhang.use_case

import com.example.appbanhang.repository.AuthReponsitory
import javax.inject.Inject

class FirebaseSignUp @Inject constructor(
    private val responsitory: AuthReponsitory
) {
    operator fun invoke(email:String,password:String,name:String,userName:String)= responsitory.firebaseSignUp(email,password,userName,name)
}