package com.yuliyang.hometest.sensor

import android.app.Service
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Binder
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity

/**
 * 获取手机旋转方向
 */
class SensorCalService : Service(), SensorEventListener {

    private var orientationDegress = .0
    private var gravitySensor: FloatArray? = null
    private var geomagneticSensor: FloatArray? = null
    private var matrix = FloatArray(9)
    private var rotation = FloatArray(3)

    private val mSensorManager by lazy {
        getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager
    }

    override fun onBind(intent: Intent?): IBinder? {
        //注册加速度传感器监听
        val acceleSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        mSensorManager.registerListener(this, acceleSensor, SensorManager.SENSOR_DELAY_NORMAL)
        //注册磁场传感器监听
        val magSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        mSensorManager.registerListener(this, magSensor, SensorManager.SENSOR_DELAY_NORMAL)
        return MyBinder()
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

    /**
     * 计算角度
     */
    private fun calculateRotation() {
        if (gravitySensor != null && geomagneticSensor != null) {
            if (SensorManager.getRotationMatrix(matrix, null, gravitySensor, geomagneticSensor)) {
                SensorManager.getOrientation(matrix, rotation);
                orientationDegress = rotation[2] * 180f / Math.PI
                println("orientationDegress   $orientationDegress")
            }
        }
    }

    inner class MyBinder : Binder() {

        fun getOrientationDegress(): Double {
            return orientationDegress
        }
    }
}