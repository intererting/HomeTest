package com.yuliyang.hometest

import android.os.Bundle
import android.text.PrecomputedText
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    val fragment = CoroutinesFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageData = workDataOf("data" to "haha")
        val uploadWorkRequest = PeriodicWorkRequestBuilder<TestWorker>(16, TimeUnit.MINUTES)
                .setInputData(imageData)
                .build()


        WorkManager.getInstance().getWorkInfosForUniqueWorkLiveData("unique").observe(this, Observer {
            it?.apply {
                if (it.isEmpty()) {
                    println("empty")
                } else {
                    println("unique  ${get(0).state}")
                }
            }
        })

        WorkManager.getInstance().enqueueUniquePeriodicWork(
                "unique",
                ExistingPeriodicWorkPolicy.REPLACE,
                uploadWorkRequest
        )
    }

    fun jump(view: View) {
//        startActivity(Intent(this, SecondActivity::class.java))
        fragment.show(supportFragmentManager, "")
    }


}
