package com.example.appbanhang.use_case

import com.example.appbanhang.repository.AuthReponsitory
import javax.inject.Inject

class FirebaseSignIn @Inject constructor(
    private val responsitory: AuthReponsitory
) {
    operator fun invoke(email:String,password:String)= responsitory.firebaseSignIn(email,password)
}