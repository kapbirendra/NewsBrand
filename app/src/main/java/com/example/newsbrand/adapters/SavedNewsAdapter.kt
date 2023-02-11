package com.example.newsbrand.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsbrand.R
import com.example.newsbrand.response.saved_response.SavedArticle
import com.example.newsbrand.viewmodel.SavedFragmentViewModel
import java.util.*
import kotlin.collections.ArrayList

class SavedNewsAdapter(
    private val savedArticles: List<SavedArticle>,
    private val context: Context,
    private val savedNewsFragmentViewModel: SavedFragmentViewModel
) :RecyclerView.Adapter<SavedNewsAdapter.MyViewHolder>(),Filterable {

    private var articleTemp = mutableListOf<SavedArticle>()
    init {
        articleTemp.addAll(savedArticles)
    }

    inner class MyViewHolder(view:View):RecyclerView.ViewHolder(view){
        val newsHeading: TextView = view.findViewById(R.id.news_heading_SFA)
        val publishedAt: TextView = view.findViewById(R.id.news_date_SFA)
        val newsImage: ImageView = view.findViewById(R.id.imageViewSFA)
        val layout:LinearLayout = view.findViewById(R.id.readFullNews_SLA)
        val deleteNews:ImageButton = view.findViewById(  R.id.delete_Saved)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutView = LayoutInflater.from(parent.context).inflate(R.layout.saved_list_adapter_views,parent,false)
        return MyViewHolder(layoutView)
    }

    override fun getItemCount(): Int {
        return articleTemp.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.newsHeading.text = articleTemp[position].title
        holder.publishedAt.text = articleTemp[position].publishedAt!!.substring(1,10)
        Glide.with(context).load(articleTemp[position].urlToImage).into(holder.newsImage)

        holder.deleteNews.setOnClickListener {
            savedNewsFragmentViewModel.deleteArticleFromVm(savedArticles[position])
            articleTemp.removeAt(position)
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()){
                    articleTemp = savedArticles.toMutableList()
                }else{
                    val resultList = ArrayList<SavedArticle>()
                    for (row in savedArticles){
                        if (row.title!!.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    articleTemp = resultList
                }
                val filterResult = FilterResults()
                filterResult.values = articleTemp
                return filterResult
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                articleTemp = results?.values as MutableList<SavedArticle>
                notifyDataSetChanged()
            }

        }

    }

}