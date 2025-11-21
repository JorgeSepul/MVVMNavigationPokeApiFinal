package com.example.mvvmnavigation.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val firebaseAuth : FirebaseAuth = FirebaseAuth.getInstance()

    fun getCurrentUser() : FirebaseUser? {
        return firebaseAuth.currentUser
    }

    suspend fun register(email:String, password:String) : Result<FirebaseUser> = try{
        val res = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        res.user?.let { Result.success(it) } ?: Result.failure(Exception("Error al registrar usuario"))
    } catch (e: Exception) {
        Result.failure(e)
    }

    suspend fun login(email:String, password:String) : Result<FirebaseUser> = try{
        val res = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        res.user?.let { Result.success(it) } ?: Result.failure(Exception("Error al iniciar sesión"))
    } catch (e: Exception) {
        Result.failure(e)
    }



    fun logOut(){
        firebaseAuth.signOut()
    }

    fun isUsserLogIn() : Boolean{
        return firebaseAuth.currentUser!=null
    }
}