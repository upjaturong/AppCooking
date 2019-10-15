package com.example.cooking

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity :AppCompatActivity(){
    var textView: TextView? = null
    var imageView: ImageView? = null
    var textfood : TextView?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        Canceled.setOnClickListener {
            var btnCanceled = Intent(this, MainActivity::class.java)
            startActivity(btnCanceled)
        }
        Confirm.setOnClickListener {
            var btnConfirm = Intent(this, TrackingOrder::class.java)
            startActivity(btnConfirm)
        }

        textView = findViewById(R.id.textView)
        imageView = findViewById(R.id.imageView)
        textfood = findViewById(R.id.textfood)
        var intent = getIntent()
        textView!!.setText(""+intent.getStringExtra("NAMEFOOD"))
        imageView!!.setImageResource(intent.getIntExtra("IMAGE",0))
        textfood!!.setText(Html.fromHtml(getString(intent.getIntExtra("TEXTFOOD",0)),Html.FROM_HTML_MODE_COMPACT))

    }
}




