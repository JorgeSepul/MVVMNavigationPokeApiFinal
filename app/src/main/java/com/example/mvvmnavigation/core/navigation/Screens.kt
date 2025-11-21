package com.example.mvvmnavigation.core.navigation

import kotlinx.serialization.Serializable
//si no mandamos parámetros a la pantalla podemos usar objetos
@Serializable
object Login
@Serializable
object Registro
@Serializable
object Inicio

@Serializable
object ScreenDatos
//si queremos mandar parámetros necesitamos una data class
@Serializable
data class Vista(val email : String, val pass : String)
//si necesitamos pasar algo más pesado, un objeto, un array, lo hacemos pasando id y utilizando sharedPrefs