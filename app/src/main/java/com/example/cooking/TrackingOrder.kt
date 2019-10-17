package com.example.cooking

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.trackorder.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.timer
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Handler
import android.view.View


class TrackingOrder :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trackorder)

        val minute:Long = 1000 * 60 // 1000 milliseconds = 1 second

        // 2 hours 35 minutes 50 seconds
        val millisInFuture:Long =  (minute * 10) + (1000 * 50)

        // Count down interval 1 second
        val countDownInterval:Long = 1000
        timer(millisInFuture,countDownInterval).start()

        Handler().postDelayed({
            step_1.setBackgroundResource(R.drawable.bg3)

        }, 5000)

        Handler().postDelayed({
            step_2.setBackgroundResource(R.drawable.bg3)
            img_step.setBackgroundResource(R.drawable.img_2)
        },10000)

        Handler().postDelayed({
            step_3.setBackgroundResource(R.drawable.bg3)
            img_step.setBackgroundResource(R.drawable.imgtracking_3)
        },15000)



        gotomenu.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }


    private fun timer(millisInFuture:Long,countDownInterval:Long):CountDownTimer{
        return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){
                val timeRemaining = timeString(millisUntilFinished)
                    text_view.text = timeRemaining
            }
            override fun onFinish() {
                text_view.text = "Done"
            }

        }
    }



    // Method to get days hours minutes seconds from milliseconds
    private fun timeString(millisUntilFinished:Long):String{

        var millisUntilFinished:Long = millisUntilFinished
        val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
        millisUntilFinished -= TimeUnit.HOURS.toMillis(hours)

        val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
        millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes)

        val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)

        // Format the string
        return String.format(
            Locale.getDefault(),
            " %02d : %02d : %02d ",
           hours, minutes,seconds
        )
    }

}

