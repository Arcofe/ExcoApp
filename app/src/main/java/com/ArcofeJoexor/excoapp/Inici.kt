package com.ArcofeJoexor.excoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Inici: AppCompatActivity(), View.OnClickListener {
    enum class ProviderType{
        BASIC
    }

    private var btnAjustes: ImageButton? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.inici)

            btnAjustes = findViewById<ImageButton>(R.id.ajustesInici)
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
        when(v?.id){
            R.id.ajustesInici -> {
                val intent = Intent(this, Ajustes::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}