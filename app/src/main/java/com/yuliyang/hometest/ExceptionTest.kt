package com.yuliyang.hometest

import kotlinx.coroutines.*
import java.lang.IllegalArgumentException

//suspend fun main() {
//    try {
//        Thread(Runnable {
//            Thread.sleep(2000)
//            throw IllegalAccessException()
//        }).start()
//    } catch (e: Exception) {
//        println("catch")
//        e.printStackTrace()
//    }


//    val test = GlobalScope.launch {
//        val job = async(Dispatchers.IO) {
//            Thread(Runnable {
//                Thread.sleep(2000)
//                println("after thread sleep")
//            })
//            3
//        }
//        println("before await")
//        println(job.await())
//        println("after await")
//    }
//
//    test.join()
//}


//fun main() = runBlocking {
//    //sampleStart
//    val handler = CoroutineExceptionHandler { _, exception ->
//        println("Caught $exception")
//    }
//
//    val job = GlobalScope.launch(handler) {
//        try {
//            launch {
//                launch {
//                    throw IllegalArgumentException("yichang")
//                }
//                println("1")
//            }
//            println("2")
//        } catch (e: Exception) {
//            println("try catch")
//        }
//    }
//
//    job.join()
////sampleEnd
//}

@ExperimentalCoroutinesApi
fun main() = runBlocking {
    val job = GlobalScope.launch {
        val result = suspendCancellableCoroutine<String> {
            Thread(Runnable {
                Thread.sleep(5000)
                it.resume("haha") {
                    println(it.message)
                }
            }).start()
        }
        println(result)
    }
    job.join()
}