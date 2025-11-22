package com.example.mvvmnavigation.data.repository

import com.example.mvvmnavigation.data.models.Pokemon
import com.example.mvvmnavigation.data.network.PokeAPI
import com.example.mvvmnavigation.data.network.RetrofitClient
import retrofit2.Retrofit

class PokemonRepository {
    private val api = RetrofitClient.pokeAPI

    suspend fun getPokemonList() : ArrayList<Pokemon>?{
        return try {
            api.getPokemons().results
        }catch (e: Exception){
             ArrayList() //devuelvo un array vacío
        }

    }
}