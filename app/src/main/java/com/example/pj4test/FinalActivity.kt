package com.example.pj4test

import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.os.Bundle
import android.widget.Toast
import android.content.Intent
import android.telephony.SmsManager
import android.view.View
import android.widget.Button
//import com.example.pj4test.SubActivity
import java.lang.Exception
import kotlin.system.exitProcess

class FinalActivity : AppCompatActivity() {
    private var buttonSend: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.finalactivity_main)
        buttonSend = findViewById<View>(R.id.buttonSend) as Button
        buttonSend!!.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}