package com.example.mvvmnavigation.data.models

import com.google.gson.annotations.SerializedName

data class PokemonResponse (
    @SerializedName("results") var results: ArrayList<Pokemon>
)
