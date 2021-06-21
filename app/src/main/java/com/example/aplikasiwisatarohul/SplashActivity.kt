package com.example.aplikasiwisatarohul

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.aplikasiwisatarohul.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    companion object {
        const val SPLASH_TIME_OUT: Long = 3000
    }

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }, SPLASH_TIME_OUT)
    }
}