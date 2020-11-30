package com.example.sopt_1st_seminar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_recycler_view.*

class SecondFragment : Fragment() {

    private lateinit var sampleAdapter: SampleAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_second, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
    }

    fun initView(view:View){
        sampleAdapter = SampleAdapter(view.context)
        recyclerView = view.findViewById(R.id.main_rcv)
        main_rcv.layoutManager = LinearLayoutManager(context)
        main_rcv.adapter = sampleAdapter

        sampleAdapter.data = mutableListOf(
            SampleData("이름","조성림","작성 날짜 : 2020.10.17","안녕하세요, 팟장님"),
            SampleData("나이","22","작성 날짜 : 2020.10.17","항상 유익한 세미나 감사합니다"),
            SampleData("파트","안드로이드","작성 날짜 : 2020.10.17","아주 조금... 어렵지만"),
            SampleData("GitHub","https://github.com/CHOSUNGRIM","작성 날짜 : 2020.10.17","열심히 할게요"),
            SampleData("SOPT","www.sopt.org","작성 날짜 : 2020.10.17","안드로이드 짱")
        )

        sampleAdapter.notifyDataSetChanged()
    }
}