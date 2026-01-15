package com.jalagama.newsapp_mvvm_architecture.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jalagama.newsapp_mvvm_architecture.databinding.NewsSourceItemLayoutBinding
import com.jalagama.newsapp_mvvm_architecture.ui.newsList.NewsListActivity
import com.jalagama.newsapp_mvvm_architecture.ui.newsList.NewsType

class CountriesAdapter(private val countries: List<Pair<String, String>>) :
    RecyclerView.Adapter<CountriesAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DataViewHolder(
        NewsSourceItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: DataViewHolder,
        position: Int,
    ) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    class DataViewHolder(private val binding: NewsSourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(country: Pair<String, String>) {
            binding.souceItem.text = country.second
            binding.souceItem.setOnClickListener {
                val context = binding.root.context
                context.startActivity(
                    NewsListActivity.newIntent(
                        context,
                        country.first,
                        NewsType.NEWS_COUNTRIES.name
                    )
                )

            }
        }
    }
}