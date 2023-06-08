package com.example.finalproject.viewmodel

//import com.bumptech.glide.load.engine.Resource

import androidx.lifecycle.ViewModel
import com.example.finalproject.data.User
import com.example.finalproject.util.*
import com.example.finalproject.util.Constants.USER_COLLECTION
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
 class RegisterViewModel @Inject constructor (

    private val firebaseAuth:FirebaseAuth,
    private val dp:FirebaseFirestore

):ViewModel(){
    private val _register = MutableStateFlow<Resource<User>>(Resource.Unspecified())
    val register: Flow<Resource<User>> = _register

    private val _validation= Channel<RegisterFieldState>()
    val validate=_validation.receiveAsFlow()
    fun createAccountWithEmailAndPassword(user: User , password:String){
        if(checkValidation(user, password)) {
            runBlocking {
                _register.emit(Resource.Loading())
            }
            firebaseAuth.createUserWithEmailAndPassword(user.email, password)
                .addOnSuccessListener { it ->
                    it.user?.let {
//                        _register.value = Resource.Success(it)
                        saveUserInfo(it.uid,user)
                    }
                }.addOnFailureListener {
                    _register.value = Resource.Error(it.message.toString())

                }
        }else{
            val registerFieldState=RegisterFieldState(
                validateEmail(user.email), validatePassword(password)
            )
            runBlocking {
                _validation.send(registerFieldState)
            }
        }

    }

    private fun saveUserInfo(userUid: String,user: User) {
        dp.collection(USER_COLLECTION)
            .document(userUid)
            .set(user)
            .addOnSuccessListener{
                _register.value=Resource.Success(user)
            }.addOnFailureListener{
                _register.value = Resource.Error(it.message.toString())
            }
    }

    private fun checkValidation(user: User, password: String): Boolean {
        val emailValidation = validateEmail(user.email)
        val passwordValidation = validatePassword(password)
        return (emailValidation is RegisterValidation.Success
                && passwordValidation is RegisterValidation.Success)
    }
}