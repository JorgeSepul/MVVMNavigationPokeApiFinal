package com.example.mvvmnavigation.data.repository

import com.example.mvvmnavigation.data.models.Pokemon
import com.example.mvvmnavigation.data.network.PokeAPI
import com.example.mvvmnavigation.data.network.RetrofitClient
import retrofit2.Retrofit

class PokemonRepository {
    private val api = RetrofitClient.pokeAPI

    suspend fun getPokemonList() : ArrayList<Pokemon>?{
        val call = api.getPokemons().execute()

        val body = call.body()
        if(call.isSuccessful){
            return body?.results //devuelvo el array de jedais
        }else{
            return ArrayList<Pokemon>() //devuelvo un array vacío
        }

    }
    suspend fun obtenerDatos(retrofit: Retrofit): String {
        return try {
            val response = retrofit.create(PokeAPI::class.java).getPokemons().execute()
            if (response.isSuccessful) {
                val pokemons = response.body()
                pokemons?.results?.getOrNull(2)?.name ?: "Sin nombre"
            } else {
                "Error: ${response.code()}"
            }
        } catch (e: Exception) {
            "Excepción: ${e.message ?: "Error desconocido"}"
        }
    }
}