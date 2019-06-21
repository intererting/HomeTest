package com.yuliyang.hometest

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.yuliyang.hometest.TipsUtil.showToast
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.lang.ref.WeakReference
import kotlin.coroutines.CoroutineContext

class TipsActiivty : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + SupervisorJob() + Job()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tips)
//        val mLayoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
//        mLayoutParams.width = 300
//        mLayoutParams.height = 300
//        val view = View(this).apply {
//            layoutParams = mLayoutParams
//            setBackgroundColor(resources.getColor(R.color.colorAccent))
//        }
//        showToast(view)
    }

    override fun onResume() {
        super.onResume()
//        try {
//            Thread(Runnable {
//                Thread.sleep(5000)
//                throw IllegalAccessException()
//            }).start()
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
//        val handler = CoroutineExceptionHandler { _, exception ->
//            println("Caught $exception")
//        }
//        launch(handler) {
//            with(CoroutineScope(coroutineContext + SupervisorJob())) {
//                println("1")
////            supervisorScope {
//                launch {
//                    println("2")
//                    throw  IllegalArgumentException("异常A")
//                }
//                launch {
//                    println("3")
//                    throw  IllegalStateException("异常B")
//                }
//                launch {
//                    repeat(1000) {
//                        delay(1000)
//                        println("repeat")
//                    }
//                }
//            }
////            }
//        }

        testException()
    }

    private fun testException() {
        launch {
            val handler = CoroutineExceptionHandler { _, exception ->
                println("Caught $exception with suppressed ${exception.suppressed.contentToString()}")
            }
            val job = GlobalScope.launch(handler) {
                launch {
                    try {
                        delay(Long.MAX_VALUE)
                    } finally {
                        throw ArithmeticException()
                    }
                }
                launch {
                    delay(100)
                    throw IOException()
                }
                delay(Long.MAX_VALUE)
            }
            job.join()
        }
    }
}

object TipsUtil : LifecycleObserver {

    fun AppCompatActivity.showToast(tipsView: View) {
        lifecycle.addObserver(this@TipsUtil)
        val rootView = window.decorView as? ViewGroup
        val tipsContentView = FrameLayout(applicationContext).apply {
            layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                gravity = Gravity.CENTER
            }
            tag = localClassName
        }
        tipsContentView.addView(tipsView)
        rootView?.addView(tipsContentView)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun AppCompatActivity.dismissToast() {
        val rootView = window.decorView as? ViewGroup
        rootView?.apply {
            removeView(findViewWithTag<View>(localClassName))
        }
    }

    class DismissHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
        }
    }
}

//fun main() = runBlocking {
//    val handler = CoroutineExceptionHandler { _, exception ->
//        println("Caught $exception with suppressed ${exception.suppressed.contentToString()}")
//    }
//    val job = GlobalScope.launch(handler) {
//        launch {
//            try {
//                delay(Long.MAX_VALUE)
//            } finally {
//                throw ArithmeticException()
//            }
//        }
//        launch {
//            delay(100)
//            throw IOException()
//        }
//        delay(Long.MAX_VALUE)
//    }
//    job.join()
//}

fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    val job = launch(handler) {
        supervisorScope() {
            val childA = launch {
                throw AssertionError()
            }
//            val childB = launch {
//                throw IllegalArgumentException()
//            }
            repeat(1000) {
                delay(1000)
                println("repeat")
            }
        }
    }
}

