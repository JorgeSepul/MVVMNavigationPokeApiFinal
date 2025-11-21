package com.example.mvvmnavigation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun RegisterScreen(NavigateToLogin:()->Unit){
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text("Registro")
            TextField(value = email, onValueChange = {x->email = x})
            TextField(value = pass, onValueChange = {pass=it})
            Button(onClick = {
                //validaciones de registro
                NavigateToLogin()   } ) {
                Text("Vista")
            }
        }


    }
}