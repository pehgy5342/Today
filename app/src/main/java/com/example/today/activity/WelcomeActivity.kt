package com.example.today.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.example.today.R
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Glide.with(this).load(R.drawable.home).into(iv_welcome)

        val intent = Intent(this, MainActivity::class.java)
        Handler().postDelayed(Runnable {
            intent.putExtra("Hello", "hello")
            startActivity(intent)
            finish()

        }, 2000)


    }
}
