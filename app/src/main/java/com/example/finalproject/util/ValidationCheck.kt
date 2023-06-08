package com.example.finalproject.util

import android.util.Patterns

fun validateEmail(email:String):RegisterValidation{
    if(email.isEmpty())
        return RegisterValidation.Failed("Email can not be empty")
    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return RegisterValidation.Failed("Wrong email format")
    return RegisterValidation.Success
}
fun validatePassword(password:String):RegisterValidation{
    if(password.isEmpty())
        return RegisterValidation.Failed("Password can not be empty")
    if(password.length<6)
        return RegisterValidation.Failed("password should have at least 6 char")
    return RegisterValidation.Success
}