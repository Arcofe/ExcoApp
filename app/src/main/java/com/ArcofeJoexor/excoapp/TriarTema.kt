package com.ArcofeJoexor.excoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView

class TriarTema: AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tema)
        //declaracion botones
        var atras = findViewById<ImageView>(R.id.atras)
        atras.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.atras -> {
                this.finish()
            }
        }
    }
}