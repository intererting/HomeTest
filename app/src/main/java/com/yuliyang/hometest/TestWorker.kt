package com.yuliyang.hometest

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class TestWorker(appContext: Context, workerParams: WorkerParameters) : Worker(appContext, workerParams) {

    override fun doWork(): Result {

        println(Thread.currentThread())
        println("${SimpleDateFormat("HH:mm:ss").format(Date())}  ${inputData.getString("data")}")

        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://www.baidu.com")
            .build()

        val response = client.newCall(request).execute()

        val outFile = File(applicationContext.cacheDir, "test.txt")
        outFile.writeBytes(response.body()?.bytes() ?: byteArrayOf())

        return Result.success()
    }
}