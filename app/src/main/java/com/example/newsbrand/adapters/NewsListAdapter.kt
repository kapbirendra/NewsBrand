package com.example.newsbrand.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsbrand.api.NewsOnclick
import com.example.newsbrand.databinding.NewListAdapterBinding
import com.example.newsbrand.response.Article
import com.example.newsbrand.response.saved_response.SavedArticle
import com.example.newsbrand.response.saved_response.SourceSaved
import com.example.newsbrand.viewmodel.SavedFragmentViewModel
import java.util.*
import kotlin.collections.ArrayList

class NewsListAdapter (
    private val articles: List<Article>,
    private val newsOnclick: NewsOnclick,
    private val savedFragmentViewModel: SavedFragmentViewModel
) : RecyclerView.Adapter<NewsListAdapter.MyViewHolder>(),Filterable {
    lateinit var binding: NewListAdapterBinding
    private lateinit var context: Context
    private var articleTemp = mutableListOf<Article>()
    init {
        articleTemp.addAll(articles)
    }

    inner class MyViewHolder() : RecyclerView.ViewHolder(binding.root) {
        val newsHeading: TextView = binding.newsHeadingNLA
        val newsDescription: TextView = binding.newDescNLA
        val newsDate: TextView = binding.newsDateNLA
        val newsImage: ImageView = binding.imageViewNLA
        val notSavedBookMark: ImageView = binding.notSavedBookmark
        val clickNewsLayout: ConstraintLayout = binding.layoutNewsListNLA
        init {
            binding.root.setOnClickListener {
                newsOnclick.newClick(adapterPosition)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = NewListAdapterBinding.inflate(inflater, parent, false)
        context = parent.context
        return MyViewHolder()
    }

    override fun getItemCount(): Int {
        return articleTemp.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.newsHeading.text = articleTemp[position].title
        holder.newsDescription.text = articleTemp[position].description
        holder.newsDate.text = articleTemp[position].publishedAt!!.substring(0, 10)
        Glide.with(context).load(articleTemp[position].urlToImage).into(holder.newsImage)

        val artPosition = articles[position]
        val dummySource = SourceSaved(artPosition.source.id,artPosition.source.name)
        val dummyData = SavedArticle(artPosition.id,artPosition.author,artPosition.content,artPosition.description,artPosition.publishedAt
            ,dummySource,artPosition.title,artPosition.url,artPosition.urlToImage)

        holder.notSavedBookMark.setOnClickListener {
                savedFragmentViewModel.addArticlesFromVm(dummyData)
                Toast.makeText(context, "added..", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()){
                    articleTemp = articles.toMutableList()
                }else{
                    val resultList = ArrayList<Article>()
                    for (row in articles){
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
                articleTemp = results?.values as kotlin.collections.ArrayList<Article>
                notifyDataSetChanged()
            }

        }

    }
}
