package com.yuliyang.hometest

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class AutoCloseKeyboardActivity : AppCompatActivity() {

    private val im by lazy {
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_close_keyboard)
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
                            this is EditText
                    ) {
                        im.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                    }
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}