package com.example.appbanhang.use_case

import com.example.appbanhang.repository.AuthReponsitory
import javax.inject.Inject

class FirebaseSignIn @Inject constructor(
    private val repository: AuthReponsitory
) {
    operator fun invoke(email:String,password:String)= repository.firebaseSignIn(email,password)
}