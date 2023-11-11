package com.example.newsly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsly.databinding.ItemLayoutBinding

class NewsAdapter:RecyclerView.Adapter<NewsAdapter.NewsAdapterViewHolder>() {

    inner class NewsAdapterViewHolder(val binding: ItemLayoutBinding):RecyclerView.ViewHolder(binding.root)

    private var list = ArrayList<Articles>()

    fun setList(list: List<Articles>){
        this.list = list as ArrayList<Articles>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapterViewHolder {
        return NewsAdapterViewHolder(
            ItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: NewsAdapterViewHolder, position: Int) {
        holder.binding.newsDiscription.text = list[position].description
        holder.binding.newsTittle.text = list[position].title
        Glide.with(holder.itemView.context).load(list[position].urlToImage).into(holder.binding.newsImage)
    }


}