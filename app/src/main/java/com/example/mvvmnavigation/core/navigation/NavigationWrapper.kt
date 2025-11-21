package com.example.mvvmnavigation.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.isPopupLayout
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mvvmnavigation.ui.screens.DatosScreen
import com.example.mvvmnavigation.ui.screens.InicioScreen
import com.example.mvvmnavigation.ui.screens.LoginScreen
import com.example.mvvmnavigation.ui.screens.RegisterScreen
import com.example.mvvmnavigation.ui.screens.VistaScreen
import com.example.mvvmnavigation.ui.viewmodels.LoginViewModel
import com.example.mvvmnavigation.ui.viewmodels.VistaViewModel

@Composable
fun NavigationWrapper() {
//encargado de tener toda la navegación
    val navController = rememberNavController()
//donde estarán todas las rutas que utilizará nuestra app
    NavHost(navController= navController, startDestination = Inicio){
//declaramos la vista login, entre <> va el nombre del objeto de Screens.kt
        composable<Inicio> {
            InicioScreen(
                NavigateToLogin = { navController.navigate(Login) },
                NavigateToRegistro = {navController.navigate(Registro)}
            )
        }
        composable<Login> {
            val viewModel : LoginViewModel = viewModel()

            LoginScreen (viewModel, {navController.navigate(ScreenDatos)})
        }

        composable<Registro> {
            RegisterScreen({navController.navigate(Login)})
        }

        composable<ScreenDatos>{
            DatosScreen({ navController.navigate(Vista) })
        }
        composable<Vista> {
            val vm: VistaViewModel = viewModel()
            VistaScreen (
                navigateToInicio = {
                    navController.navigate(Inicio) {
                        popUpTo(Inicio) { inclusive = false }
                        launchSingleTop = true
                    }
                },
                vistaViewModel = vm
            )
        }
    }
}

