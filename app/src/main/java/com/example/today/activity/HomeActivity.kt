package com.example.today.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.bumptech.glide.Glide
import com.example.today.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Glide.with(this).load(R.drawable.welcome).into(iv_welcome)

        val intent = Intent(this, MainActivity::class.java)
        Handler().postDelayed(Runnable {
            intent.putExtra("Hello", "hello")
            startActivity(intent)
            finish()

        }, 2000)


    }
}
