package com.example.appbanhang.use_case
import com.example.appbanhang.model.User
import com.example.appbanhang.repository.AuthReponsitory
import com.example.appbanhang.until.Response
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import me.tatarka.inject.annotations.Inject
class AuthReponsitoryImpl @Inject constructor(
    private val auth: FirebaseAuth,
    private val firestore : FirebaseFirestore

): AuthReponsitory
{
    var  Success : Boolean = false
    override fun isUserAuthFireBase(): Boolean {
        return auth.currentUser!=null
    }

    override fun getFirebaseAuthState(): Flow<Boolean>  = callbackFlow {
        val authStateListener=FirebaseAuth.AuthStateListener{
            trySend(auth.currentUser == null)
        }
        auth.addAuthStateListener(authStateListener)
        awaitClose{
            auth.removeAuthStateListener(authStateListener)
        }
    }

    override fun firebaseSignIn(email: String, password: String): Flow<Response<Boolean>> = flow{
        Success=false
        try{
            emit(Response.Loading)
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener{
                Success=true;
            }.await()
            emit(Response.Success(Success))
        }
        catch(e:Exception)
        {
            emit(Response.Error(e.localizedMessage?:"Đoạn code trên bị lỗi!"))
        }
    }

    override fun firebaseSignOut(): Flow<Response<Boolean>> = flow{
        try{
            emit(Response.Loading)
            auth.signOut()
            emit(Response.Success(true))
        }
        catch(e:Exception)
        {
            emit(Response.Error(e.localizedMessage?:"Đoạn code trên bị lỗi!"))
        }
    }

    override fun firebaseSignUp(
        email: String,
        password: String,
        userName: String,
        name: String
    ): Flow<Response<Boolean>> = flow{
        Success = false
        try{
            emit(Response.Loading)
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener{
                Success=true
            }
            if(Success)
            {
                val userId = auth.currentUser?.uid!!
                val obj = User().apply {
                    this.name = name
                    this.nameId = userId
                    this.userName = userName
                    this.password = password
                    this.email = email
                }
                firestore.collection("USER").document(userId).set(obj).addOnSuccessListener{

                }.await()
                emit(Response.Success(Success))
            }
            else{
                Response.Success(Success)
            }
        }
        catch(e:Exception)
        {
            emit(Response.Error(e.localizedMessage?:"Đoạn code trên bị lỗi!"))
        }
    }
}