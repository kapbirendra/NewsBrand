package com.example.newsbrand.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsbrand.databinding.NewListAdapterBinding
import com.example.newsbrand.response.Article

class NewsListAdapter(private val articles: List<Article>) :RecyclerView.Adapter<NewsListAdapter.MyViewHolder>() {
    lateinit var binding: NewListAdapterBinding
    private lateinit var context:Context

    inner class MyViewHolder():RecyclerView.ViewHolder(binding.root){
        val newsHeading:TextView = binding.newsHeadingNLA
        val newsDescription:TextView = binding.newsHeadingNLA
        val newsImage:ImageView = binding.imageViewNLA
        val clickNewsLayout: ConstraintLayout = binding.layoutNewsListNLA

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = NewListAdapterBinding.inflate(inflater,parent,false)
        context = parent.context
        return MyViewHolder()
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.newsHeading.text = articles[position].title
        Glide.with(context).load(articles[position].urlToImage).into(holder.newsImage)
        holder.clickNewsLayout.setOnClickListener {
            Toast.makeText(context, "${articles[position].title }...", Toast.LENGTH_SHORT).show()
        }
    }
}