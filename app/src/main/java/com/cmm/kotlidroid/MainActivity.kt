package com.cmm.kotlidroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cmm.kotlidroid.android.AndroidActivity
import com.cmm.kotlidroid.firebase.FirebaseActivity
import com.cmm.kotlidroid.retrofit.RetrofitActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun launchAndroidActivity(view: View) {
        startActivity(Intent(this, AndroidActivity::class.java))
    }

    fun launchRetrofitActivity(view: View) {
        startActivity(Intent(this, RetrofitActivity::class.java))
    }

    fun launchFirebaseActivity(view: View) {
        startActivity(Intent(this, FirebaseActivity::class.java))
    }
}
