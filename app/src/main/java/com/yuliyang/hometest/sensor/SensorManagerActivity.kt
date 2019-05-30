package com.yuliyang.hometest.sensor

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class SensorManagerActivity : AppCompatActivity(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job

    private var calServiceBinder: SensorCalService.MyBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val calService = Intent(this, SensorCalService::class.java)
        bindService(calService, object : ServiceConnection {
            override fun onServiceDisconnected(name: ComponentName?) {

            }

            override fun onServiceConnected(name: ComponentName?, service: IBinder) {
                calServiceBinder = service as SensorCalService.MyBinder
            }
        }, Context.BIND_AUTO_CREATE)
    }

    override fun onResume() {
        super.onResume()
//        launch {
//            repeat(5000) {
//                delay(1000)
//                println(calServiceBinder?.getOrientationDegress())
//            }
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}