package com.example.mvvmnavigation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmnavigation.data.models.Pokemon
import com.example.mvvmnavigation.data.models.PokemonResponse
import com.example.mvvmnavigation.data.repository.AuthRepository
import com.example.mvvmnavigation.data.repository.PokemonRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList


class VistaViewModel : ViewModel(){
    private val authRepository : AuthRepository = AuthRepository()
    private val pokemonRepository : PokemonRepository = PokemonRepository()


    private val _pokemonList = MutableLiveData<ArrayList<Pokemon>>()
    val pokemonList : LiveData<ArrayList<Pokemon>> = _pokemonList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage : LiveData<String?> = _errorMessage
    private val _currentIndex = MutableLiveData(0)
    val currentIndex: LiveData<Int> = _currentIndex
    fun loadPokemonList(){
        viewModelScope.launch {
            _isLoading.value=true

            if(pokemonRepository.getPokemonList() == null){
                _errorMessage.value = "Error al obtener datos"
            }else{
                _pokemonList.value = pokemonRepository.getPokemonList()
            }


            _isLoading.value = false
        }
    }
    fun nextPokemon(){
        val list = _pokemonList.value ?: emptyList()
        if(list.isNotEmpty()){
            val next = ((_currentIndex.value ?: 0) + 1) % list.size
            _currentIndex.value = next
        }
    }
    fun getCurrentUser(): FirebaseUser? {
        return authRepository.getCurrentUser()
    }
}

