package com.example.coroutinesexample

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity2 : AppCompatActivity() {

    private lateinit var nextBtnView: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        nextBtnView = findViewById(R.id.nextBtn)
        nextBtnView.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}