package com.yuliyang.hometest.task

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yuliyang.hometest.R
import kotlinx.android.synthetic.main.activity_b.*

class B_Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b)
        println(taskId)
        jumpC.setOnClickListener {
            startActivity(Intent(this@B_Activity, C_Activity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK or
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT)
            })
        }

        println("B_Activity  onCreate")
    }

    override fun onResume() {
        super.onResume()
        println("B_Activity  onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("B_Activity  onDestroy")
    }
}