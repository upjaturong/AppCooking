package com.example.cooking

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.order.*
import kotlinx.android.synthetic.main.trackorder.*

class Order :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.order)

        paynow.setOnClickListener {
            Toast.makeText(this,"Thank You ",Toast.LENGTH_LONG).show()
            startActivity(Intent(this,MainActivity::class.java))
        }

    }
}

