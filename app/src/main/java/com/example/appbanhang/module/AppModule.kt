package com.example.appbanhang.module

import com.example.appbanhang.data.AuthData
import com.example.appbanhang.repository.AuthReponsitory
import com.google.firebase.auth.FirebaseAuth
import com.example.appbanhang.use_case.AuthReponsitoryImpl
import com.example.appbanhang.use_case.FirebaseAuthState
import com.example.appbanhang.use_case.FirebaseSignIn
import com.example.appbanhang.use_case.FirebaseSignOut
import com.example.appbanhang.use_case.FirebaseSignUp
import com.example.appbanhang.use_case.IsUserAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Định nghĩa các dependency ở đây
    @Singleton
    @Provides
    fun provideAuthReponsitory(auth: FirebaseAuth, firestore:FirebaseFirestore): AuthReponsitory {
        return AuthReponsitoryImpl(auth = auth,firestore=firestore)
    }
    @Singleton
    @Provides
    fun provideAuthUseCase(repository:AuthReponsitoryImpl)= AuthData(
        isUserAuth = IsUserAuth(repository = repository),
        firebaseAuthState= FirebaseAuthState(repository=repository),
        firebaseSignOut = FirebaseSignOut(repository=repository),
        firebaseSignIn= FirebaseSignIn(repository=repository),
        firebaseSignUp = FirebaseSignUp(repository=repository)
    )

}
