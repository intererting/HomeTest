package com.yuliyang.hometest

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : AppCompatActivity() {

    private val im by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        test.setOnClickListener {
            println("click")
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                currentFocus?.apply {
                    val focusRect = Rect()
                    getGlobalVisibleRect(focusRect)
                    if (im.isActive &&
                            !focusRect.contains(ev.x.toInt(), ev.y.toInt()) &&
                            windowToken != null &&
                            this is EditText) {
                        im.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}