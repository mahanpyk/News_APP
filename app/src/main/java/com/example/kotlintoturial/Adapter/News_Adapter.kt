package com.example.kotlintoturial.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.kotlintoturial.Model.Models
import com.example.kotlintoturial.R
import com.squareup.picasso.Picasso

class News_Adapter(val context: Context, val news: List<Models>) : RecyclerView.Adapter<News_Adapter.Holder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        if (true) {
            val view: View = LayoutInflater.from(context).inflate(R.layout.rv_item, p0, false)
            return Holder(view)
        } else {
            val view: View = LayoutInflater.from(context).inflate(R.layout.loading_item, p0, false)
            return Holder(view)
        }
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bindnews(news.get(p1))
    }

    override fun getItemCount(): Int {
        return news.size/2
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title = itemView.findViewById<TextView>(R.id.tv_itemnews_title)
        var description = itemView.findViewById<TextView>(R.id.tv_itemnews_description)
        var date = itemView.findViewById<TextView>(R.id.tv_itemnews_date)
        var publisher = itemView.findViewById<TextView>(R.id.tv_itemnews_author)
        var image = itemView.findViewById<ImageView>(R.id.iv_item_news)

        fun bindnews(news: Models) {
            title.text = news.title
            description.text = news.description
            date.text = news.publisher
            publisher.text = news.author
            if (news.urlToImage != null) {
                Picasso.get().load(news.urlToImage).placeholder(R.mipmap.news_placeholder).resize(800, 300)
                        .into(image)
            }
        }
    }
}
