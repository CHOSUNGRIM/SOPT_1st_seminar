package com.example.sopt_1st_seminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var sampleAdapter: SampleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        sampleAdapter = SampleAdapter(this)

        main_rcv.adapter = sampleAdapter
        main_rcv.layoutManager = LinearLayoutManager(this)

        sampleAdapter.data = mutableListOf(
            SampleData("이름", "조성림"),
            SampleData("나이","22"),
            SampleData("파트", "안드로이드")
        )
    }
}