package com.example.cooking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.user_registration.*

class LoginActivity : AppCompatActivity() {

    var mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        showHome()
        mDatabase = FirebaseDatabase.getInstance().getReference("Names")
        create_account.setOnClickListener {
            showRegistration()
            save.setOnClickListener {
                registerUser ()
            }
        }
        login_button.setOnClickListener {
            login()
        }
        login_register.setOnClickListener {
            showHome()
        }
    }

    private fun login () {

        val emailTxt = findViewById<View>(R.id.login_email) as EditText
        val email = emailTxt.text.toString()

        val passwordTxt = findViewById<View>(R.id.login_password) as EditText
        val password = passwordTxt.text.toString()

        if (!email.isEmpty() && !password.isEmpty()) {
            this.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener ( this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val uid = user!!.uid
                    mDatabase.child(uid).child("Status").setValue(1)
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Successfully Logged in :)", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error Logging in :(", Toast.LENGTH_SHORT).show()
                }
            }
        }else {
            Toast.makeText(this, "Please fill up the Credentials :|", Toast.LENGTH_SHORT).show()
        }
    }

    private fun registerUser () {
        val emailTxt = findViewById<View>(R.id.email) as EditText
        val passwordTxt = findViewById<View>(R.id.password_register) as EditText
        val nameTxt = findViewById<View>(R.id.name) as EditText

        val email = emailTxt.text.toString()
        val password = passwordTxt.text.toString()
        val name = nameTxt.text.toString()
        val restname = rest_name.text.toString()

        if (!email.isEmpty() && !password.isEmpty() && !name.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val uid = user!!.uid
                    mDatabase.child(uid).child("Name").setValue(name)
                    mDatabase.child(uid).child("Restaurant").setValue(restname)
                    mDatabase.child(uid).child("Status").setValue(1)
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Successfully registered :)", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this, "Error registering, try again later :(", Toast.LENGTH_LONG).show()
                }
            }
        }else {
            Toast.makeText(this,"Please fill up the Credentials :|", Toast.LENGTH_LONG).show()
        }
    }

    private fun showRegistration(){
        registration_layout.visibility = View.VISIBLE
        home_11.visibility = View.GONE
    }


    private fun showHome(){
        registration_layout.visibility = View.GONE
        home_11.visibility = View.VISIBLE
    }
}
