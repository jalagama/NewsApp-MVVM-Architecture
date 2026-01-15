package com.jalagama.newsapp_mvvm_architecture.ui.Languages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jalagama.newsapp_mvvm_architecture.databinding.NewsSourceItemLayoutBinding
import com.jalagama.newsapp_mvvm_architecture.ui.NewsList.NewsListActivity
import com.jalagama.newsapp_mvvm_architecture.ui.NewsList.NewsType

class LanguagesAdapter(private val languages: List<Pair<String, String>>) :
    RecyclerView.Adapter<LanguagesAdapter.DataViewHolder>() {


    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(languages[position])
    }

    override fun getItemCount(): Int {
        return languages.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        NewsSourceItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    class DataViewHolder(private val binding: NewsSourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(language: Pair<String, String>) {
            binding.souceItem.text = language.second
            binding.souceItem.setOnClickListener {
                val context = binding.root.context

                context.startActivity(
                    NewsListActivity.newIntent(
                        context,
                        language.first,
                        NewsType.NEWS_LANGUAGE.name
                    )
                )

            }

        }

    }
}