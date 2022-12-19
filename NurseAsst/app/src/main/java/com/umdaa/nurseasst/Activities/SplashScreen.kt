package com.umdaa.nurseasst.Activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.umdaa.nurseasst.R

class SplashScreen : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 4000 // 3 sec


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT > 16) {
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        setContentView(R.layout.activity_main)


        Handler().postDelayed({

            startActivity(Intent(this, HomeScreenActivity::class.java))
           // finish()
        }, SPLASH_TIME_OUT)
    }
}
