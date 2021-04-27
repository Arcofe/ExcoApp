package com.ArcofeJoexor.excoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Registre: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registre)
        //declaracion botones
        var btnVolverInicio = findViewById<Button>(R.id.btnBackLogIn)
        btnVolverInicio.setOnClickListener(this)
    }
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnBackLogIn ->{
                this.finish()
            }
        }
    }
}