package com.example.manymanymanycats

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.manymanymanycats.api.CatInfo
import com.example.manymanymanycats.databinding.ActivityMainBinding
import com.example.manymanymanycats.databinding.CatItemBinding

class CatsAdapter(val callback:(page:Int)->Unit, val tap:(catInfo:CatInfo)->Unit) : RecyclerView.Adapter<CatsAdapter.CatViewHolder>() {
    class CatViewHolder(val binding: CatItemBinding):ViewHolder(binding.root) {
        val image= binding.ivPhoto
    }

    var list = ArrayList<CatInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val binding = CatItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return CatViewHolder(binding)
    }

    override fun getItemCount()=list.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val item=list[position]
        if (position==list.size-5){
            callback(list.size/10)
        }
        holder.image.setOnClickListener {
            tap(item)
        }
        if (item.image == null) {
            Glide.with(holder.binding.root.context)
                .load(item.url)
                .centerCrop()
                .into(holder.image)
        } else {
            Glide.with(holder.binding.root.context)
                .load(item.image.url)
                .centerCrop()
                .into(holder.image)
        }
    }
}