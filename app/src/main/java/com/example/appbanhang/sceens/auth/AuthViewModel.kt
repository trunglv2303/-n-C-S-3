package com.example.appbanhang.sceens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.appbanhang.data.AuthData
import com.example.appbanhang.until.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authUsecase : AuthData
): ViewModel(){
    val isUserAuth get()=authUsecase.isUserAuth()
    private val _signInState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signInState : State<Response<Boolean>> = _signInState

    private val _signUpState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signUpState : State<Response<Boolean>> = _signUpState

    private val _signOutState = mutableStateOf<Response<Boolean>>(Response.Success(false))
    val signOutState : State<Response<Boolean>> = _signOutState
    private val _firebaseAuthState=mutableStateOf<Boolean>(false)
    val firebaseAuthState : State<Boolean> = _firebaseAuthState
    fun getFirebaseState()
    {
        viewModelScope.launch {
            authUsecase.firebaseAuthState().collect{
                _firebaseAuthState.value=it
            }
        }
    }
    fun signOut(){
        viewModelScope.launch {
            authUsecase.firebaseSignOut().collect{
                _signOutState.value=it
                if(it==Response.Success(true))
                {
                    _signInState.value=Response.Success(false)
                }
            }
        }
    }
    fun signIn(email:String,password:String)
    {
        viewModelScope.launch {
            authUsecase.firebaseSignIn(email=email,password=password).collect{
                _signInState.value=it
            }
        }
    }
    fun signUp(email:String,password:String,userName:String,name:String)
    {
        viewModelScope.launch {
            authUsecase.firebaseSignUp(email=email,password=password,name=name,userName=userName).collect{
                _signUpState.value=it
            }
        }
    }
}