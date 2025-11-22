package com.example.mvvmnavigation.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mvvmnavigation.ui.screens.DatosScreen
import com.example.mvvmnavigation.ui.screens.InicioScreen
import com.example.mvvmnavigation.ui.screens.LoginScreen
import com.example.mvvmnavigation.ui.screens.RegisterScreen
import com.example.mvvmnavigation.ui.screens.VistaScreen
import com.example.mvvmnavigation.ui.viewmodels.LoginViewModel
import com.example.mvvmnavigation.ui.viewmodels.RegisterViewModel
import com.example.mvvmnavigation.ui.viewmodels.VistaViewModel

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController= navController, startDestination = Inicio){
        composable<Inicio> {
            InicioScreen(
                navigateToLogin = { navController.navigate(Login) },
                navigateToRegistro = {navController.navigate(Registro)}
            )
        }
        composable<Login> {
            val viewModel : LoginViewModel = viewModel()

            LoginScreen (viewModel, {navController.navigate(ScreenDatos)})
        }

        composable<Registro> {
            val viewModel: RegisterViewModel = viewModel()
            RegisterScreen(viewModel,  {navController.navigate(Login)})
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

