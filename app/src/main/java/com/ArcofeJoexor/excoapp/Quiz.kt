package com.ArcofeJoexor.excoapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Quiz: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz)
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}