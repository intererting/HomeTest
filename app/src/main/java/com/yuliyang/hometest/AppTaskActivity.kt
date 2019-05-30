package com.yuliyang.hometest

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AppTaskActivity : AppCompatActivity() {

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appTasks = activityManager.appTasks
        println("栈的个数  ${appTasks.size}")
        for (task in appTasks) {
            println(task.taskInfo.topActivity.className)
        }
    }
}