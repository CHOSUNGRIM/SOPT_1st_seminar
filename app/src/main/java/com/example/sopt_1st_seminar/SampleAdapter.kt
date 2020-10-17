package com.example.sopt_1st_seminar

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
    }
}