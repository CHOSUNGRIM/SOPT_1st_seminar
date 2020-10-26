package com.example.sopt_1st_seminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val aTitle = intent.getStringExtra("iTitle")
        val aSubtitle = intent.getStringExtra("iSubtitle")
        val aDate = intent.getStringExtra("iDate")
        val aDescription = intent.getStringExtra("iDescription")

        detail_title_txt.setText(aTitle)
        detail_subtitle_txt.setText(aSubtitle)
        detail_date_txt.setText(aDate)
        detail_description_txt.setText(aDescription)
    }
}