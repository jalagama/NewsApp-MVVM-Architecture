package com.jalagama.newsapp_mvvm_architecture.ui.NewsSource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.jalagama.newsapp_mvvm_architecture.data.model.Sources
import com.jalagama.newsapp_mvvm_architecture.databinding.NewsSourceItemLayoutBinding

class NewsSourceAdapter(private val newsSourceList: ArrayList<Sources>) :
    RecyclerView.Adapter<NewsSourceAdapter.DataViewHolder>() {


    class DataViewHolder(private val binding: NewsSourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sources: Sources) {
            binding.souceItem.text = sources.name
            binding.souceItem.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, sources.url.toUri())
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