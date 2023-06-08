package com.example.finalproject.util

import com.example.finalproject.util.RegisterValidation as RegisterValidation

sealed class RegisterValidation(){
    object Success: RegisterValidation()
    data class Failed(val message:String): RegisterValidation()

}
 data class RegisterFieldState(
    val email: RegisterValidation,
     val password: RegisterValidation
 )