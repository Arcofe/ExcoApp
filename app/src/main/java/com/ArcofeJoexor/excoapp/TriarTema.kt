package com.ArcofeJoexor.excoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView

class TriarTema: AppCompatActivity(), View.OnClickListener {

    private var btnAtras: ImageView? = null
    private var btnAjustes: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tema)
        //declaracion botones
        btnAtras = findViewById<ImageView>(R.id.atras)
        btnAjustes = findViewById<ImageButton>(R.id.ajustesTema)
        btnAtras!!.setOnClickListener(this)
        btnAjustes!!.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.atras -> {
                val intent = Intent(this, Inici::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

            R.id.ajustesTema -> {
                val intent = Intent(this, Ajustes::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}