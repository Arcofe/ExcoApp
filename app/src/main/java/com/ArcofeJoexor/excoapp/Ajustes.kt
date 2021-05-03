package com.ArcofeJoexor.excoapp

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Ajustes: AppCompatActivity(), View.OnClickListener {

    private var btnAtras: ImageButton? = null
    private var btnLogOut: Button? = null
    private var btnConfigUser: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ajustes)

        btnAtras = findViewById<ImageButton>(R.id.atras)
        btnLogOut = findViewById<Button>(R.id.Logout)
        btnConfigUser = findViewById<Button>(R.id.ConfigUser)
        btnAtras!!.setOnClickListener(this)
        btnLogOut!!.setOnClickListener(this)
        btnConfigUser!!.setOnClickListener(this)

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
                this.finish()
            }

            R.id.ConfigUser -> {
                val intent = Intent(this, User_Config::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

            R.id.Logout -> {
                AuthUI.getInstance().signOut(this).addOnSuccessListener {
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(baseContext, "LOGOUT", Toast.LENGTH_LONG).show()
                    this.finish()
                }
            }
        }
    }
}