package com.jalagama.newsapp_mvvm_architecture.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jalagama.newsapp_mvvm_architecture.data.model.Article
import com.jalagama.newsapp_mvvm_architecture.databinding.TopHeadlineItemLayoutBinding

class NewsSearchResultsAdapter(private val articles: ArrayList<Article>) :
    RecyclerView.Adapter<NewsSearchResultsAdapter.DataViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DataViewHolder {

        return DataViewHolder(
            TopHeadlineItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: DataViewHolder,
        position: Int,
    ) {
        holder.bind(articles[position])
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    fun addData(list: List<Article>) {
        articles.clear()
        articles.addAll(list)
    }

    class DataViewHolder(private val binding: TopHeadlineItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.textViewTitle.text = article.title
            binding.textViewDescription.text = article.description
            binding.textViewSource.text = article.source.name
            Glide.with(binding.imageViewBanner.context)
                .load(article.urlToImage)
                .into(binding.imageViewBanner)

            binding.imageViewBanner.setOnClickListener {
                val builder = CustomTabsIntent.Builder()
                val customTabsIntent = builder.build()
                customTabsIntent.launchUrl(it.context, article.url.toUri())
            }

        }
    }

}