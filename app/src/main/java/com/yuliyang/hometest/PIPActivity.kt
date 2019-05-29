package com.yuliyang.hometest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_pip.*

class PIPActivity : AppCompatActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        println("PIP onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pip)
        testPIP.setOnClickListener {
            //            enterPictureInPictureMode()
            val workIntent = Intent()
            workIntent.putExtra("work", "work num: 1");
            TestJobIntentService.enqueueWork(this, workIntent);
        }
    }

    override fun onResume() {
        super.onResume()
        println("PIP onResume")
    }

    override fun onPause() {
        super.onPause()
        println("PIP onPause")
    }

    override fun onStop() {
        super.onStop()
        println("PIP onStop")
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        println("PIP onSaveInstanceState")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("PIP onDestroy")
    }
}