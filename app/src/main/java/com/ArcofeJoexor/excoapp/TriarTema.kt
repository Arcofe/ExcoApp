package com.ArcofeJoexor.excoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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

        /**
         * Part on canviem dinamicament un TextView
         */
        var currentuser = Firebase.auth.currentUser
        var usuario = findViewById<TextView>(R.id.usuario)
        val user = currentuser?.email?.split('@')
        usuario.text = user?.get(0)

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