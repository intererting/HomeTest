package com.yuliyang.hometest

import android.annotation.SuppressLint
import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_drag_and_drop.*

class DragAndDropActivity : AppCompatActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_drop)
        dragView.setOnLongClickListener {
            val clipData = ClipData.newPlainText("testDrog", "dragTest")//这只是其中一种方式，还可以传递更丰富的数据
            val myShadow = View.DragShadowBuilder(dragView)
            dragView.startDragAndDrop(clipData, myShadow, null, 0)
        }
        destView.setOnDragListener { v, event ->
            when (event.action) {
                DragEvent.ACTION_DROP -> {
                    println(event.clipData)
                }
            }
            return@setOnDragListener true
        }
    }
}