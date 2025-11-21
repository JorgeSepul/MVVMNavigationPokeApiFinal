package com.example.mvvmnavigation.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmnavigation.data.repository.AuthRepository
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel(){
    private val authRepository = AuthRepository()

    private val _email = MutableLiveData<String>()
    val email : LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password : LiveData<String> = _password

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage : LiveData<String?> = _errorMessage

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _isRegisterOk = MutableLiveData<Boolean>()
    val isRegisterOk : LiveData<Boolean> = _isRegisterOk

    fun onEmailChange(newEmail : String){
        _email.value = newEmail
    }

    fun onPasswordChange(newPassword : String){
        _password.value = newPassword
    }

    fun onRegister(){
        val emailValue = _email.value ?: ""
        val passwordValue = _password.value ?: ""

        if(emailValue.isBlank() || passwordValue.isBlank()){
            //poner todas las comprobaciones
            _errorMessage.value = "Error al insertar datos"
            return
        }

        viewModelScope.launch {
            _isLoading.value=true
            val  result = authRepository.register(emailValue, passwordValue)

            result.onSuccess {
                _isRegisterOk.value = true
            }.onFailure { e ->
                _errorMessage.value = e.message
            }


            _isLoading.value = false
        }


    }
}