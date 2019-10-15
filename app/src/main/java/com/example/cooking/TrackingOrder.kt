package com.example.cooking

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.trackorder.*

class TrackingOrder :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trackorder)
        gotomenu.setOnClickListener {
            var btngotoMemu = Intent(this, MainActivity::class.java)
            startActivity(btngotoMemu)
        }
    }
}

