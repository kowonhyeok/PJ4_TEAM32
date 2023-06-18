package com.example.pj4test

import android.Manifest.permission.*
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.SmsManager
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"

    // permissions
    private val permissions = arrayOf(RECORD_AUDIO, CAMERA, SEND_SMS)
    private val PERMISSIONS_REQUEST = 0x0000001;
    var phoneNo: String? = null
    var sms: String? = null
    var first: Boolean = true
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        phoneNo = intent.getStringExtra("phoneNo")
        sms = intent.getStringExtra("sms")

        checkPermissions() // check permissions
    }

    fun sendSms() {
        if (first) {
            try {
                val smsManager: SmsManager = this.getSystemService(SmsManager::class.java)
                //val smsManager: SmsManager = SmsManager.getDefault()
                val smsWithId: String = sms.plus("-- 이 학생은 부정행위가 의심됩니다.")
                smsManager.sendTextMessage(phoneNo, null, smsWithId, null, null)
                Toast.makeText(applicationContext, "감독관에게 문자가 전송 되었습니다.", Toast.LENGTH_LONG).show()
                first = false
                val intent = Intent(applicationContext, FinalActivity::class.java)
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
    private fun checkPermissions() {
        if (permissions.all{ActivityCompat.checkSelfPermission(this, it) == PackageManager.PERMISSION_GRANTED}){
            Log.d(TAG, "All Permission Granted")
        }
        else{
            requestPermissions(permissions, PERMISSIONS_REQUEST)
        }
    }
}