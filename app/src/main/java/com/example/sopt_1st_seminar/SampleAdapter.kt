package com.example.sopt_1st_seminar

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.profile_item_list.view.*
import java.security.AccessControlContext

class SampleAdapter (private val context : Context) : RecyclerView.Adapter<SampleViewHolder>(){
    var data = listOf<SampleData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SampleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.profile_item_list, parent, false)

        return SampleViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: SampleViewHolder, position: Int) {
        holder.onBind(data[position])

        holder.itemView.setOnClickListener {
            val model = data.get(position)
            var gTitle : String = model.title
            var gSubtitle : String = model.subTitle
            var gDate : String = model.date
            var gDescription : String = model.description

            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("iTitle", gTitle)
            intent.putExtra("iSubtitle", gSubtitle)
            intent.putExtra("iDate", gDate)
            intent.putExtra("iDescription", gDescription)

            context.startActivity(intent)
        }
    }
}