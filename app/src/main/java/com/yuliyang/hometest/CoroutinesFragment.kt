package com.yuliyang.hometest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CoroutinesFragment : DialogFragment(), CoroutineScope {

    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_second, container, false)
    }

    override fun onResume() {
        super.onResume()
        if (job.isCancelled) {
            job = Job()
        }
        launch {
            println("Coroutines Fragment onResume")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy")
        job.cancel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("onDestroyView")
    }
}