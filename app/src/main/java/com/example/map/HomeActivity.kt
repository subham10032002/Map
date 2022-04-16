package com.example.map

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    var email: String? = null
    var name: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        home.setOnClickListener {
            val fragment = HomeFragment()

                supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()

        }

        nav.setOnClickListener {
            val fragment = NavvFragment()

            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()

        }

        user.setOnClickListener {
            val fragment = UserFragment()

            supportFragmentManager.beginTransaction().replace(R.id.frameLayout, fragment).commit()

        }






    }
}