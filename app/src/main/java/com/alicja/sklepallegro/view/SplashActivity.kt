package com.alicja.sklepallegro.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.alicja.sklepallegro.R

class SplashActivity : AppCompatActivity() {

    companion object {
        const val SPLASH_TIME_DURATION: Long = 2000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            kotlin.run {
                val homeIntent = Intent (this, HomeActivity::class.java)
                startActivity(homeIntent)
                finish()
            }
        }, SPLASH_TIME_DURATION)
    }
}
