package com.yuliyang.hometest.security

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yuliyang.hometest.R
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * jetpack security
 */
class SecurityActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)
        testCoroutines()
    }

    private fun testCoroutines() {
        scheduleIoMain(execution = {
            println(Thread.currentThread())
            "haha"
        }, callback = {
            print(it)
            println("callback   ${Thread.currentThread()}")
        })
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

}

fun <R> CoroutineScope.scheduleIoMain(
    execution: CoroutineScope.() -> R,
    callback: (R) -> Unit
) {
    launch(Dispatchers.Main) {
        callback(withContext(Dispatchers.IO) {
            execution()
        })
    }
}

infix fun <T> Deferred<T>.then(block: (T) -> Unit): Job {

    return GlobalScope.launch(context = Dispatchers.Main) {

        block(this@then.await())
    }
}