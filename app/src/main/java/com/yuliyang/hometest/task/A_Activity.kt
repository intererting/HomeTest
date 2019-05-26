package com.yuliyang.hometest.task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yuliyang.hometest.R
import kotlinx.android.synthetic.main.activity_a.*

class A_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a)
        println(taskId)
        jumpB.setOnClickListener {
            startActivity(Intent(this@A_Activity, B_Activity::class.java))
            finishAndRemoveTask()
        }
    }

    override fun onResume() {
        super.onResume()
        println("A_Activity  onResume")
    }
}