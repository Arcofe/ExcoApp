package com.ArcofeJoexor.excoapp

import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI

class Ajustes: AppCompatActivity(), View.OnClickListener {

    private var btnAtras: ImageButton? = null
    private var btnLogOut: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ajustes)

        btnAtras = findViewById<ImageButton>(R.id.atras)
        btnLogOut = findViewById<Button>(R.id.Logout)
        btnAtras!!.setOnClickListener(this)
        btnLogOut!!.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.atras -> {
                val intent = Intent(this, Inici::class.java)
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