package com.example.cooking

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.order.*
import kotlinx.android.synthetic.main.trackorder.*

class Order :AppCompatActivity(){

    var mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase : DatabaseReference

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order)
        mDatabase = FirebaseDatabase.getInstance().getReference("Foods")

        val nameTxt = findViewById<View>(R.id.name_order) as TextView

        val priceTxt = findViewById<TextView>(R.id.price)
        val priceTotal = findViewById<TextView>(R.id.total)
        val count = findViewById<TextView>(R.id.count)

        val user = mAuth.currentUser
        val uid = user!!.uid

        paynow.setOnClickListener {
            Toast.makeText(this,"Thank You ",Toast.LENGTH_LONG).show()
            mDatabase.child(uid).child("Food").setValue("")
            mDatabase.child(uid).child("Price").setValue(0)
            mDatabase.child(uid).child("Count").setValue(0)
            startActivity(Intent(this,MainActivity::class.java))
        }


        mDatabase.child(uid).child("Food").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                nameTxt.text = " " + snapshot.value.toString()
            }
        })
        mDatabase.child(uid).child("Price").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }

            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                priceTxt.text = " " + snapshot.value.toString()
                priceTotal.text = " " + snapshot.value.toString()
            }
        })
        mDatabase.child(uid).child("Count").addValueEventListener(object :
            ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
            }
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                count.text = " " + snapshot.value.toString()
            }
        })
    }
}

