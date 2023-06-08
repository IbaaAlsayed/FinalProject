package com.example.finalproject.fregmant
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.finalproject.data.User
import com.example.finalproject.databinding.RegisterFragmantBinding
import com.example.finalproject.util.RegisterValidation
import com.example.finalproject.util.Resource
import com.example.finalproject.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private const val TAG="RegisterFragment"
@AndroidEntryPoint
class RegisterFragment:Fragment() {
    private lateinit var binding: RegisterFragmantBinding
    private val viewModel by viewModels<RegisterViewModel> ()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=RegisterFragmantBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonReg.setOnClickListener{
                val user=User(
                    firstname.text.toString().trim(),
                    lastname.text.toString().trim(),
                    email.text.toString().trim(),

                    )
                val pass=password.text.toString().trim()
                viewModel.createAccountWithEmailAndPassword(user,pass)


            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.register.collect {
                when(it){
                    is Resource.Loading ->{
                        binding.buttonReg.startAnimation()
                    }
                    is Resource.Success ->{
                        Log.d("text",it.data.toString())
                        binding.buttonReg.revertAnimation()

                    }
                    is Resource.Error ->{
                        Log.e(TAG,it.message.toString())
                        binding.buttonReg.revertAnimation()


                    }
                    else -> Unit
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.validate.collect{ validation->
                if(validation.email is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.email.apply {
                            requestFocus()
                            error= validation.email.message
                        }
                    }
                }
                if(validation.password is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.password.apply {
                            requestFocus()
                            error= validation.password.message
                        }
                    }
                }
            }
        }

    }
}

