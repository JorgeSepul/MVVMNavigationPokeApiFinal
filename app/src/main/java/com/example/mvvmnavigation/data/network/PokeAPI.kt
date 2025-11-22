package com.example.mvvmnavigation.data.network

import com.example.mvvmnavigation.data.models.Pokemon
import com.example.mvvmnavigation.data.models.PokemonResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface PokeAPI {
    @Headers("Accept: application/json")
// Método para obtener todos los pokemon
    @GET("pokemon?limit=100")
    suspend fun getPokemons(): PokemonResponse
    // Método para obtener una pokemon por su ID
    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int): Call<Pokemon>
}