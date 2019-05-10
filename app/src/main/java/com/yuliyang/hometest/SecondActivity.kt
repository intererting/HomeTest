package com.yuliyang.hometest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yuliyang.hometest.CoroutinesObj.test
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class SecondActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        launch(Dispatchers.IO) {
            test()
        }
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}
