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

class SubActivity : AppCompatActivity() {
    private var buttonSend: Button? = null
    private var textPhoneNo: EditText? = null
    private var textSMS: EditText? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.subactivity_main)
        buttonSend = findViewById<View>(R.id.buttonSend) as Button
        textPhoneNo = findViewById<View>(R.id.editTextPhoneNo) as EditText
        textSMS = findViewById<View>(R.id.editTextSMS) as EditText

        buttonSend!!.setOnClickListener {
            val phoneNo = textPhoneNo!!.text.toString()
            val sms = textSMS!!.text.toString()
            try {
                //전송
                val smsWithId : String = sms.plus(" 학생 입장했습니다")
                val smsManager: SmsManager = this.getSystemService(SmsManager::class.java)
                smsManager.sendTextMessage(phoneNo, null, smsWithId, null, null)
                Toast.makeText(applicationContext, "전송 완료!", Toast.LENGTH_LONG).show()
                val intent = Intent(applicationContext, MainActivity::class.java)
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("sms", sms);
                startActivity(intent)
            } catch (e: Exception) {
                Toast.makeText(
                    applicationContext,
                    "SMS failed, please try again later!",
                    Toast.LENGTH_LONG
                ).show()
                e.printStackTrace()
            }
        }
    }
}