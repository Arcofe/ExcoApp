package com.ArcofeJoexor.excoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Inici: AppCompatActivity(), View.OnClickListener {
    enum class ProviderType{
        BASIC
    }

    private var btnAjustes: ImageButton? = null
    private var btnJugar: Button? = null
    private var btnRanking: Button? = null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.inici)

            btnAjustes = findViewById<ImageButton>(R.id.ajustesInici)
            btnJugar = findViewById<Button>(R.id.jugar)
            btnRanking = findViewById<Button>(R.id.ranking)
            btnAjustes!!.setOnClickListener(this)
            btnJugar!!.setOnClickListener(this)
            btnRanking!!.setOnClickListener(this)

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

            R.id.jugar -> {
                val intent = Intent(this, Quiz::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

            R.id.ranking -> {
                val intent = Intent(this, Ranking::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}