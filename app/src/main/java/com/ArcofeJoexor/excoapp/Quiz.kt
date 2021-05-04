package com.ArcofeJoexor.excoapp

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Quiz: AppCompatActivity(), View.OnClickListener {

    private var Pregunta: TextView? = null
    private var Resposta1: RadioButton? = null
    private var Resposta2: RadioButton? = null
    private var Resposta3: RadioButton? = null
    private var Resposta4: RadioButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quiz)

        /**
         * Part on canviem dinamicament un TextView
         */
        var currentuser = Firebase.auth.currentUser
        var usuario = findViewById<TextView>(R.id.usuario)
        val user = currentuser?.email?.split('@')
        usuario.text = user?.get(0)

        Pregunta = findViewById<TextView>(R.id.preguntes)
        Resposta1 = findViewById<RadioButton>(R.id.respuesta1)
        Resposta2 = findViewById<RadioButton>(R.id.respuesta2)
        Resposta3 = findViewById<RadioButton>(R.id.respuesta3)
        Resposta4 = findViewById<RadioButton>(R.id.respuesta4)
        Pregunta!!.setText("hola")
        Resposta1!!.setText("Resposta1")
        Resposta2!!.setText("Resposta2")
        Resposta3!!.setText("Resposta3")
        Resposta4!!.setText("Resposta4")
    }

    override fun onClick(v: View?) {
        TODO("Not yet implemented")
    }
}