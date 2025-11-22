package com.example.mvvmnavigation.ui.viewmodels

import android.util.Log
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


    private val _pokemonList = MutableLiveData<List<Pokemon>>(emptyList())
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage : LiveData<String?> = _errorMessage
    private val _currentIndex = MutableLiveData(0)
    val currentIndex: LiveData<Int> = _currentIndex

    init {
        loadPokemonList()
    }
    fun loadPokemonList() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val result = pokemonRepository.getPokemonList()
                Log.d("VistaViewModel", "Pokemons loaded: ${result?.size}")

                _pokemonList.value = result!!
                _isLoading.value = false
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _isLoading.value = false
            }
        }
    }

    fun nextPokemon(){
        val list = _pokemonList.value ?: emptyList()
        if(!list.isNullOrEmpty()){
            _currentIndex.value  = ((_currentIndex.value ?: 0) + 1) % list.size

        }
    }
    fun getCurrentUser(): FirebaseUser? {
        return authRepository.getCurrentUser()
    }
}

