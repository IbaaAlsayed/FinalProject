package com.example.finalproject.fregmant

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.activities.ShoppingActivity
import com.example.finalproject.databinding.LoginFragmantBinding
import com.example.finalproject.dialog.setupBottomSheetDialog
import com.example.finalproject.util.Resource
import com.example.finalproject.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment:Fragment(R.layout.login_fragmant) {
    private lateinit var binding: LoginFragmantBinding
    private val viewModel by viewModels<LoginViewModel> ()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=LoginFragmantBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.donthaveaccount.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        binding.apply {
            buttonReg.setOnClickListener {
                val email= mailLogin.text.toString().trim()
                val password= passwordLogin.text.toString()
                viewModel.login(email, password)

            }
        }
        binding.forgetpassword.setOnClickListener{
            setupBottomSheetDialog {email->
                viewModel.resetPassword(email)

            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.resetPassword.collect{
                when(it){
                    is Resource.Loading->{
                    }
                    is Resource.Success->{
                        Snackbar.make(requireView(),"Reset link was sent to your email",Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Error->{
                        Snackbar.make(requireView(),"Error : ${it.message}",Snackbar.LENGTH_LONG).show()
                    }
                    is Resource.Unspecified->{

                    }

                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.login.collect{
                when(it){
                    is Resource.Loading->{
                        binding.buttonReg.startAnimation()
                    }
                    is Resource.Success->{
                        binding.buttonReg.revertAnimation()
                        Intent(requireActivity(),ShoppingActivity::class.java).also{ intent->
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(intent)
                        }
                    }
                    is Resource.Error->{
                        Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
                        binding.buttonReg.revertAnimation()

                    }
                    is Resource.Unspecified->{

                    }

                }
            }
        }

    }
}