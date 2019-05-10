package com.yuliyang.hometest

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

object CoroutinesObj : CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job

    //主线程里的launch一定在主线程
    //子线程的launch会根据任务执行时间在线程池随机分配，不用自己管理，已经最优化
    fun CoroutineScope.test() {
        launch {
            println("1  ${Thread.currentThread()}")
            launch {
                println("2  ${Thread.currentThread()}")
                launch {
                    println("3  ${Thread.currentThread()}")
                    testA()
                    delay(2000)
                    println("delay launch 3")
                }
                delay(2000)
                println("delay launch 2")
            }
            delay(2000)
            println("delay launch 1")
        }
    }

    fun CoroutineScope.testA() {
        launch {
            println("4  ${Thread.currentThread()}")
            repeat(50) {
                delay(1000)
                println("in Main")
            }
        }
    }
}