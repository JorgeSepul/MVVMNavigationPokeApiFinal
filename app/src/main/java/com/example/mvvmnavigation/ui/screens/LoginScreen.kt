package com.example.mvvmnavigation.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.mvvmnavigation.data.repository.SharedRepository
import com.example.mvvmnavigation.ui.viewmodels.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel, navigateToDatos: () -> Unit) {
    val email by viewModel.email.observeAsState("")
    val pass by viewModel.password.observeAsState("")
    val errorMessage by viewModel.errorMessage.observeAsState("")
    val isLoading by viewModel.isLoading.observeAsState(false)
    val isLoginOk by viewModel.isLoginOk.observeAsState(false)

    val contexto = LocalContext.current
    val sharedRepo = remember { SharedRepository(contexto) }

    if (isLoginOk) {
        sharedRepo.saveLogin(email, pass)
        navigateToDatos()
    }
    if(isLoading){
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }

    }else{

        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                Text("Login")
                TextField(value = email, onValueChange = {x->viewModel.onEmailChange(x)})
                TextField(value = pass, onValueChange = {viewModel.onPasswordChange(it)})
                Button(onClick = {
                    viewModel.onLogin()

                } ) {
                    Text("Vista")
                }
            }

            if (!isLoginOk && !errorMessage.isNullOrBlank()) {
                Text(text = errorMessage!!)
            }
        }
    }


}