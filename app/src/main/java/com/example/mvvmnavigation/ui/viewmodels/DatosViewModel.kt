package com.example.mvvmnavigation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmnavigation.data.repository.SharedRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class DatosViewModel(
    private val sharedRepo: SharedRepository
) : ViewModel() {

    private val auth = FirebaseAuth.getInstance()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _message = MutableLiveData<String?>()
    val message: LiveData<String?> = _message

    private val _navigateToVista = MutableLiveData<Boolean>(false)
    val navigateToVista: LiveData<Boolean> = _navigateToVista

    init {
        // Precargar desde SharedPreferences
        _email.value = sharedRepo.getEmail()
        _password.value = sharedRepo.getPassword()
    }

    // Actualiza el valor de la contraseña al cambiar en el campo de texto
    fun onPasswordChange(newPass: String) {
        _password.value = newPass
    }

    // Guarda los cambios de contraseña y actualiza Firebase + SharedPreferences
    fun onSaveChanges() {
        val newPass = _password.value?.trim().orEmpty()
        val emailVal = _email.value.orEmpty()

        if (newPass.length < 6) {
            _message.value = "La contraseña debe tener al menos 6 caracteres."
            return
        }
    }
    fun onNavigated() {
        _navigateToVista.value = false
    }
}