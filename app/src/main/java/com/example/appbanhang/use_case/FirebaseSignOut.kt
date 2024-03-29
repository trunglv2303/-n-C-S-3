package com.example.appbanhang.use_case

import com.example.appbanhang.repository.AuthReponsitory
import javax.inject.Inject

class FirebaseSignOut@Inject constructor(
    private val responsitory: AuthReponsitory
) {
    operator fun invoke()=responsitory.firebaseSignOut()
}