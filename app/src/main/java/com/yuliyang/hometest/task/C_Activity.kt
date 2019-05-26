package com.yuliyang.hometest.task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yuliyang.hometest.R
import kotlinx.android.synthetic.main.activity_c.*

class C_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_c)
        println("C_Activity  onCreate")
        println(taskId)
        jumpA.setOnClickListener {
            startActivity(Intent(this@C_Activity, A_Activity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        println("C_Activity  onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("C_Activity  onDestroy")
    }
}