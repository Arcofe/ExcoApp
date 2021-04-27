package com.ArcofeJoexor.excoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var btnRegistro: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //declaracion botones
        btnRegistro = findViewById<Button>(R.id.btnRegistro)
        btnRegistro!!.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnRegistro -> {
                val intent = Intent(this, Registre::class.java)
                startActivity(intent)
            }
        }
    }
}