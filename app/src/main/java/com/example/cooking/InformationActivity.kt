package com.example.cooking

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity :AppCompatActivity(){
    var textView: TextView? = null
    var imageView: ImageView? = null
    var textfood : TextView?= null

    var mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        mDatabase = FirebaseDatabase.getInstance().getReference("Foods")
        Canceled.setOnClickListener {
            val btnCanceled = Intent(this, MainActivity::class.java)
            startActivity(btnCanceled)
        }

        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)
        textfood = findViewById(R.id.textfood)
        val user = mAuth.currentUser
        val uid = user!!.uid
        val intent = intent
        textView!!.text = ""+intent.getStringExtra("NAMEFOOD")
        textfood!!.text = getString(intent.getIntExtra("TEXTFOOD",0))
        imageView!!.setImageResource(intent.getIntExtra("IMAGE",0))

        Confirm.setOnClickListener {
            mDatabase.child(uid).child("Food").setValue(textView!!.text)
            mDatabase.child(uid).child("Price").setValue(textfood!!.text)
            mDatabase.child(uid).child("Count").setValue(1)
            startActivity(Intent(this, TrackingOrder::class.java))
        }
    }
}




