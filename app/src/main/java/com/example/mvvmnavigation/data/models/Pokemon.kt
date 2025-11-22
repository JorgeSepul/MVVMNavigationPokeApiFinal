package com.example.mvvmnavigation.data.models

data class Pokemon(val name : String = "", val url : String = "") {

    fun getNombre() : String{
        return this.name
    }

}