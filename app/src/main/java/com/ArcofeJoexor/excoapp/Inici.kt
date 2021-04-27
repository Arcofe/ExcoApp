package com.ArcofeJoexor.excoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Inici: AppCompatActivity() {
    enum class ProviderType{
        BASIC
    }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.inici)
        }
    }