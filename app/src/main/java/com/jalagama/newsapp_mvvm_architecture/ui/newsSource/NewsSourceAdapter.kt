package com.jalagama.newsapp_mvvm_architecture.ui.newsSource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jalagama.newsapp_mvvm_architecture.data.model.Sources
import com.jalagama.newsapp_mvvm_architecture.databinding.NewsSourceItemLayoutBinding
import com.jalagama.newsapp_mvvm_architecture.ui.newsList.NewsListActivity
import com.jalagama.newsapp_mvvm_architecture.ui.newsList.NewsType

class NewsSourceAdapter(private val newsSourceList: ArrayList<Sources>) :
    RecyclerView.Adapter<NewsSourceAdapter.DataViewHolder>() {


    class DataViewHolder(private val binding: NewsSourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sources: Sources) {
            binding.souceItem.text = sources.name
            binding.souceItem.setOnClickListener {
                val context = binding.root.context
                context.startActivity(NewsListActivity.newIntent(context, sources.id, NewsType.NEWS_SOURCE.name))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        NewsSourceItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(newsSourceList[position])
    }

    override fun getItemCount(): Int {
        return newsSourceList.size
    }


    fun addData(list: List<Sources>) {
        newsSourceList.addAll(list)
    }

}