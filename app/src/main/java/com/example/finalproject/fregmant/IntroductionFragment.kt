package com.example.finalproject.fregmant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.databinding.IntroductionFragmantBinding

class IntroductionFragment:Fragment(R.layout.introduction_fragmant) {
    private lateinit var binding: IntroductionFragmantBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=IntroductionFragmantBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btn.setOnClickListener {
           findNavController().navigate(R.id.action_introductionFragment_to_fragmentAccountOption)
        }
    }

}