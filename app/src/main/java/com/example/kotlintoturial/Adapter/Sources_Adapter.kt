package com.example.kotlintoturial.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlintoturial.Model.Models
import com.example.kotlintoturial.R

class Sources_Adapter(val context: Context, val sources: List<Models.Source>, val sourceViewCallback: SourceViewCallback) : RecyclerView.Adapter<Sources_Adapter.Holder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.sources_item, p0, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return sources.size
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        p0.bind(sources.get(p1), sourceViewCallback)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.tv_sourcesitem)

        fun bind(sources: Models.Source, sourceViewCallback: SourceViewCallback) {
            name.setText(sources.name)
            itemView.setOnClickListener {
                sourceViewCallback.onSourceItemClick(sources.id!!)
            }
        }
    }

    interface SourceViewCallback {
        fun onSourceItemClick(sources: String)

    }
}