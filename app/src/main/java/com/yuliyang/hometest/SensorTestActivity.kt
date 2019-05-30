package com.yuliyang.hometest

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SensorTestActivity : AppCompatActivity(), SensorEventListener {

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        
    }

    override fun onSensorChanged(event: SensorEvent?) {
    }

    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //注册加速度传感器监听
        val acceleSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, acceleSensor, SensorManager.SENSOR_DELAY_NORMAL);
        //注册磁场传感器监听
        Sensor magSensor = mSensorManager . getDefaultSensor (Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, magSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }
}