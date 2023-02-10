package com.example.newsbrand.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsbrand.R
import com.example.newsbrand.response.saved_response.SavedArticle

class SavedNewsAdapter(private val savedArticles: List<SavedArticle>,private val context:Context) :RecyclerView.Adapter<SavedNewsAdapter.MyViewHolder>() {

    inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
        val newsHeading: TextView = view.findViewById(R.id.news_heading_SFA)
        val publishedAt: TextView = view.findViewById(R.id.news_date_SFA)
        val newsImage: ImageView = view.findViewById(R.id.imageViewSFA)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.saved_list_adapter_views,parent,false)
        return MyViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return savedArticles.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.newsHeading.text = savedArticles[position].title
        holder.publishedAt.text = savedArticles[position].publishedAt!!.substring(1,10)
        Glide.with(context).load(savedArticles[position].urlToImage).into(holder.newsImage)

    }
}