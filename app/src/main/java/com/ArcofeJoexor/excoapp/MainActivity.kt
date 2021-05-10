package com.ArcofeJoexor.excoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var btnRegistro: Button? = null
    private var btnLogIn: Button? = null
    private var btnGoogle: Button? = null
    private var user: EditText? = null
    private var password: EditText? = null

    companion object{
        const val RC_SIGN_IN = 120
    }

    /**
     * Variables globales para iniciar sesión con google.
     */
    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //declaracion botones
        btnLogIn = findViewById<Button>(R.id.btnLogIn)
        btnRegistro = findViewById<Button>(R.id.btnRegistro)
        btnGoogle = findViewById<Button>(R.id.btnGoogle)
        btnLogIn!!.setOnClickListener(this)
        btnRegistro!!.setOnClickListener(this)

        /**
         * Variables para poder hacer login con google.
         */
        mAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        mAuth = FirebaseAuth.getInstance()
        btnGoogle!!.setOnClickListener{
            signIn()
        }

        // Analytics Event
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("Message","Integració de Firebase completada")
        analytics.logEvent("InitScreen",bundle)
        //Setup
        setup()
    }

    /**
     * Sign in
     *
     */
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, MainActivity.RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val exception = task.exception
            if(task.isSuccessful){
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Log.d("SignInActivity", "firebaseAuthWithGoogle:" + account.id)
                    firebaseAuthWithGoogle(account.idToken!!)
                    println("Login Google 1")
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("SignInActivity", "Google sign in failed", e)
                    // ...
                }
            }else{
                println("Login Google 2")
                Log.w("SignInActivity", exception.toString())
            }

        }
    }

    /**
     * Firebase auth with google
     *
     * @param idToken
     */
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        println("Login Google Correcte")
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("SignInActivity", "signInWithCredential:success")
                        val intent = Intent(this, Inici::class.java)
                        startActivity(intent)
                    } else {
                        println("Login Google InCorrecte")
                        // If sign in fails, display a message to the user.
                        Log.w("SignInActivity", "signInWithCredential:failure", task.exception)
                    }

                }
    }

    private fun setDataFromTexBox()
    {
        user = findViewById(R.id.user)
        password = findViewById(R.id.password)
    }

    private fun setup() {
        /**
         * Part on mirara si existeix l'usuari per inicar la sessió
         */
        btnLogIn?.setOnClickListener{
            //
            println("Login Email 1")
            setDataFromTexBox();
            if (user?.text?.isNotEmpty()!!  && password?.text?.isNotEmpty()!!){
                println("Login Email 2")
                FirebaseAuth.getInstance().signInWithEmailAndPassword(user?.text.toString(),
                        password?.text.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        println("Login Email 3")
                        showHome(it.result?.user?.email ?: "", Inici.ProviderType.BASIC)
                    }else{
                        println("Login Email 4")
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
            R.id.btnRegistro -> {
                val intent = Intent(this, Registre::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }

            R.id.btnGoogle -> {
                val intent = Intent(this,Inici::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(intent)
            }
        }
    }
}