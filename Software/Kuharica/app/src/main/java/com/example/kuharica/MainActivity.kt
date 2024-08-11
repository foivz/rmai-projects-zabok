package com.example.kuharica

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val gumb = findViewById<Button>(R.id.btnSubmitInput)
        val unosTeksta = findViewById<EditText>(R.id.etInput)
        gumb.setOnClickListener {
            if (TextUtils.isGraphic(unosTeksta.text)) {
                val intent = Intent(this, Pocetna::class.java)
                intent.putExtra("poslani_tekst", unosTeksta.text.toString())
                startActivity(intent)
            } else {
                Toast.makeText(this, "Unesite tekst!", Toast.LENGTH_SHORT).show()
            }
        }


    }


}