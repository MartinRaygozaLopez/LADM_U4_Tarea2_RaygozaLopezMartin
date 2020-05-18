package com.example.ladm_u4_tarea2_raygozalopezmartin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            var vantana = Intent(this, Main2Activity::class.java)
            startActivity(vantana)
        }

        button2.setOnClickListener {
            var vantana = Intent(this, Main3Activity::class.java)
            startActivity(vantana)
        }

    }
}
