package com.ArcofeJoexor.excoapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class Registre: AppCompatActivity(), View.OnClickListener {

    private var btnRegistre: Button? = null
    private var btnVolverInicio: Button? = null
    private var user: EditText? = null
    private var password: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registre)
        //declaracion botones
        btnVolverInicio = findViewById<Button>(R.id.btnBackLogIn)
        btnRegistre = findViewById<Button>(R.id.btnregistre)
        btnVolverInicio!!.setOnClickListener(this)
        btnRegistre!!.setOnClickListener(this)

        // Analytics Event
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("Message","Integració de Firebase completada")
        analytics.logEvent("InitScreen",bundle)

        //Setup
        setup()
    }

    private fun setDataFromTexBox()
    {
        user = findViewById(R.id.editTextTextEmailAddress)
        password = findViewById(R.id.editTextNumberPassword)
    }

    private fun setup() {
        title = "Autenticació"
        /**
         * Part per fer el Registre d'un usuari
         */
        btnRegistre?.setOnClickListener{
            setDataFromTexBox()
            if (user?.text?.isNotEmpty()!! && password?.text?.isNotEmpty()!!){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(user!!.text.toString(),
                    password!!.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", Inici.ProviderType.BASIC)
                    }else{
                        showAlert()
                    }

                }
            }
        }
    }

    /**
     * Classe d'alerta per quan tenim un error al iniciar sessió
     */
    private fun showAlert(){

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("S'ha produït un error en l'autentificació al usuari")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    /**
     * Metode que introdueix usuaris per poder iniciar sessió, la quall guardara el usuari que sera el email i el password
     */
    private fun showHome(user: String, password: Inici.ProviderType){

        val homeIntent  = Intent(this, Inici::class.java).apply {
            putExtra("user",user)
            putExtra("password",password.name)
        }
        startActivity(homeIntent)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnBackLogIn ->{
                this.finish()
            }
        }
    }
}