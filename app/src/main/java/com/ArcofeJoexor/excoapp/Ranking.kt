package com.ArcofeJoexor.excoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity


class Ranking: AppCompatActivity(), View.OnClickListener {

    private var btnAjustes: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ranking)

        btnAjustes = findViewById<ImageButton>(R.id.ajustesRanking)
        btnAjustes!!.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.ajustesRanking -> {
                val intent = Intent(this, Ajustes::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}