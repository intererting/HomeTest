package com.yuliyang.hometest

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SensorManagerActivity : AppCompatActivity(), SensorEventListener {

    private var gravitySensor: FloatArray? = null
    private var geomagneticSensor: FloatArray? = null
    private var matrix = FloatArray(9)
    private var rotation = FloatArray(3)

    private val mSensorManager by lazy {
        getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        //注册加速度传感器监听
        val acceleSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(this, acceleSensor, SensorManager.SENSOR_DELAY_NORMAL);
        //注册磁场传感器监听
        val magSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mSensorManager.registerListener(this, magSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent) {
        when (event.sensor.getType()) {
            Sensor.TYPE_ACCELEROMETER -> {
                //重力
                gravitySensor = event.values
            }
            Sensor.TYPE_MAGNETIC_FIELD -> {
                //磁场
                geomagneticSensor = event.values
                calculateRotation()
            }
        }

    }

    private fun calculateRotation() {
        if (gravitySensor != null && geomagneticSensor != null) {
            if (SensorManager.getRotationMatrix(matrix, null, gravitySensor, geomagneticSensor)) {
                SensorManager.getOrientation(matrix, rotation);
                val degree = (360f + rotation[0] * 180f / Math.PI) % 360
                println("degress   $degree")
            }
        }
    }

    override fun onPause() {
        super.onPause()
        mSensorManager.unregisterListener(this);
    }
}