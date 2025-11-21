package com.example.mvvmnavigation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvvmnavigation.data.models.Pokemon
import com.example.mvvmnavigation.ui.viewmodels.VistaViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import kotlin.collections.get


@Composable
fun VistaScreen(
    navigateToInicio: () -> Unit,
    vistaViewModel: VistaViewModel = viewModel()
) {
    val isLoading by vistaViewModel.isLoading.observeAsState(false)
    val error by vistaViewModel.errorMessage.observeAsState()
    val list by vistaViewModel.pokemonList.observeAsState(emptyList())
    val index by vistaViewModel.currentIndex.observeAsState(0)

    // Cargar la lista al mostrar la pantalla
    LaunchedEffect(Unit) { vistaViewModel.loadPokemonList() }

    val nombreActual = if (list.isNotEmpty()) list[index].getNombre() else "..."

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "VISTA - API",
                fontSize = 28.sp,
                color = Color(red = 84, green = 18, blue = 255, alpha = 255),
                modifier = Modifier.padding(bottom = 24.dp)
            )

            when {
                isLoading -> {
                    Spacer(Modifier.height(16.dp))
                    CircularProgressIndicator()
                }
                error != null -> {
                    Spacer(Modifier.height(16.dp))
                    Text(text = error ?: "Error", color = Color.Red)
                }
                else -> {
                    Spacer(Modifier.height(16.dp))
                    Text(text = "Hola $nombreActual", fontSize = 22.sp)

                    Spacer(Modifier.height(24.dp))
                    Button(
                        onClick = { vistaViewModel.nextPokemon() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(red = 181, green = 36, blue = 106),
                            contentColor = Color.White
                        ),
                        shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                            .height(55.dp),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 6.dp,
                            pressedElevation = 10.dp
                        )
                    ) {
                        Text("Siguiente Jedi")
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { navigateToInicio() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(red = 181, green = 36, blue = 106),
                    contentColor = Color.White
                ),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 12.dp)
                    .height(55.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 10.dp
                )
            ) {
                Text("Volver al inicio")
            }
        }
    }
}



