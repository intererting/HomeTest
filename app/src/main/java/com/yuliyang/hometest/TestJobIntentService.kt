package com.yuliyang.hometest

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService

class TestJobIntentService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        println("TestJobIntentService  onHandleWork")
        println(intent.getStringExtra("work"))
    }

    companion object {
        internal fun enqueueWork(context: Context, work: Intent) {
            enqueueWork(context, TestJobIntentService::class.java, 1, work)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("TestJobIntentService  onDestroy")
    }
}