package com.example.map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    val auth by lazy{
        FirebaseAuth.getInstance()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )


        Handler().postDelayed({
            if(auth.currentUser == null) {
                val intent = Intent(this, RegistrationActivity::class.java)
                finish()
                startActivity(intent)
            }
            else{
                val intent = Intent(this, HomeActivity::class.java)
                finish()
                startActivity(intent)
            }

        }, 1500)
    }
}