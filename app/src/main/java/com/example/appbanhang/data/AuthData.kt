package com.example.appbanhang.data

import com.example.appbanhang.use_case.FirebaseAuthState
import com.example.appbanhang.use_case.FirebaseSignIn
import com.example.appbanhang.use_case.FirebaseSignOut
import com.example.appbanhang.use_case.FirebaseSignUp
import com.example.appbanhang.use_case.IsUserAuth

data class AuthData(
    val isUserAuth:IsUserAuth,
    val firebaseSignUp: FirebaseSignUp,
    val firebaseSignIn :FirebaseSignIn,
    val firebaseSignOut: FirebaseSignOut,
    val firebaseAuthState: FirebaseAuthState
)
