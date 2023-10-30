package com.example.muslimpedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.muslimpedia.R
import com.example.muslimpedia.data.model.ArticlesItem
import com.example.muslimpedia.data.model.NewsResponse
import com.example.muslimpedia.databinding.ItemRowNewsBinding
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    private val listNews = ArrayList<ArticlesItem>()

    fun setData(list: List<ArticlesItem>?){
        listNews.clear()
        if(list != null){
            listNews.addAll(list)
        }
        notifyItemChanged(1,listNews.size)
    }

    class MyViewHolder(val binding: ItemRowNewsBinding) :
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(ItemRowNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = listNews.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataNews = listNews[position]

        val dateTimeString = dataNews.publishedAt
        val dateTimeFormat = SimpleDateFormat("yyy-mm-hh'T'HH:mm:ss'Z'", Locale.getDefault())
        val date = dateTimeFormat.parse(dateTimeString)
        val myDateFormat =  SimpleDateFormat("EE, dd MM | HH:MM", Locale.getDefault())

        val myDate = myDateFormat.format(date)

        holder.binding.apply {
            tvSource.text = dataNews.source.name
            tvTitle.text = dataNews.title
            tvDate.text = myDate

            val placeholder = Picasso.get()
                .load(dataNews.urlToImage)
                .fit()
                .centerInside()
                .placeholder(R.drawable.ic_logo)
                .into(imgNews)
        }
    }
}
