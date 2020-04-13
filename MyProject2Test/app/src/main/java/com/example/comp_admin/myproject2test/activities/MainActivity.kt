package com.example.comp_admin.myproject2test.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.comp_admin.myproject2test.R
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnClickListener {

    //Google Login Request Code
    private val RC_SIGN_IN = 7
    //Google Sign In Client
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    //Firebase Auth
    private lateinit var mAuth: FirebaseAuth

    private lateinit var mCallBackManager: CallbackManager
    private lateinit var btnFacebook: Button

    private lateinit var mAuthListener:FirebaseAuth.AuthStateListener



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        init()


        mAuth = FirebaseAuth.getInstance() // get Firebase Auth instance }





    }

    private fun init() {

        button_login.setOnClickListener(this)
        button_register.setOnClickListener(this)
        sign_in_button.setOnClickListener(this)
    }

    private fun initGoogle() {
        mAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this,gso)

    }

    override fun onClick(p0: View) {
        when(p0.id){
            R.id.button_register -> {
                startActivity(Intent(this, FacebookLoginActivity::class.java))
            }
            R.id.button_login -> {
                startActivity(Intent(this, LoginActivity::class.java))
            }
            R.id.sign_in_button -> {

                signIn()
            }
            R.id.button_to_facebook-> {
                Toast.makeText(this,"Auth Failed",Toast.LENGTH_LONG).show()
                startActivity(Intent(this, FacebookLoginActivity::class.java))
            }
        }
    }

    private fun signIn() {
        initGoogle()
        val signInIntent = mGoogleSignInClient.getSignInIntent()
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    firebaseAuthWithGoogle(account)
                }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("Login", "Google sign in failed", e)
                // ...
            }

        }
    }
    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d("Login", "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login", "signInWithCredential:success")
                    val user = mAuth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Login", "signInWithCredential:failure", task.exception)
                    Toast.makeText(this,"Auth Failed",Toast.LENGTH_LONG).show()
                    updateUI(null)
                }

                // ...
            }
    }
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth.currentUser
       // updateUI(currentUser)
    }
    fun updateUI(user: FirebaseUser?){
        if(user != null){
            //Do your Stuff
            Toast.makeText(this,"Hello ${user.displayName}",Toast.LENGTH_LONG).show()
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
