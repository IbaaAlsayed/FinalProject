package com.example.finalproject.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import com.example.finalproject.BuildConfig
import com.example.finalproject.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint

class LoginRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
        }

    }
}