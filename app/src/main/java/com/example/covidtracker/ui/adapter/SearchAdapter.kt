package com.example.covidtracker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.covidtracker.R
import com.example.covidtracker.model.Superhero
import kotlinx.android.synthetic.main.raw_search_heroes.view.*

class SearchAdapter(private val heroList: List<Superhero>, private val listener: OnSearchedItemClickListener, private val context: Context) : RecyclerView.Adapter<SearchAdapter.Holder>() {

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val viewHolder =
            Holder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.raw_search_heroes, parent, false)
            )
        viewHolder.itemView.setOnClickListener {
            if (viewHolder.adapterPosition != RecyclerView.NO_POSITION) {
                listener.onItemClick(viewHolder.adapterPosition)
            }
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val currItem = heroList[position]
        holder.itemView.tvName.text = currItem.name
        Glide.with(context).load(currItem.image?.url).placeholder(R.drawable.fire_flame).circleCrop().diskCacheStrategy(
            DiskCacheStrategy.ALL).into(holder.itemView.ivSearchSuperhero)
        holder.itemView.tvRace.text = currItem.appearance?.race
        holder.itemView.tvPublisher.text = currItem.biography?.publisher
    }

    override fun getItemCount(): Int {
        return heroList.size
    }
}
interface OnSearchedItemClickListener{
    fun onItemClick(position: Int)
}