package com.example.sopt_1st_seminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Adapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
             SampleData("이름","조성림","작성 날짜 : 2020.10.17","안녕하세요, 팟장님"),
             SampleData("나이","22","작성 날짜 : 2020.10.17","항상 유익한 세미나 감사합니다"),
             SampleData("파트","안드로이드","작성 날짜 : 2020.10.17","아주 조금... 어렵지만"),
             SampleData("GitHub","https://github.com/CHOSUNGRIM","작성 날짜 : 2020.10.17","열심히 할게요"),
             SampleData("SOPT","www.sopt.org","2020.10.17","작성 날짜 : 안드로이드 짱")
         )
    }
}