package com.yuliyang.hometest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuliyang.hometest.snap.ViewPagerLayoutManager
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_snap.*
import kotlinx.android.synthetic.main.item_snap.view.*

class SnapTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_snap)
        recyclerView.layoutManager = ViewPagerLayoutManager(this, LinearLayoutManager.VERTICAL)
        recyclerView.adapter = TestAdapter()
    }

    class TestAdapter : RecyclerView.Adapter<TestHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
            return TestHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_snap, parent, false))
        }

        override fun getItemCount(): Int {
            return 20
        }

        override fun onBindViewHolder(holder: TestHolder, position: Int) {
            holder.itemView.text.text = position.toString()
        }

    }


    class TestHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    }
}