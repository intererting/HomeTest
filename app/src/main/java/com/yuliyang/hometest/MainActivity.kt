package com.yuliyang.hometest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    val fragment = CoroutinesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun jump(view: View) {
//        startActivity(Intent(this, SecondActivity::class.java))
        fragment.show(supportFragmentManager, "")
    }
}
