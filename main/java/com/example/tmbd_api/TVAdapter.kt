package com.example.tmbd_api

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tmbd_api.data.network.TmdbService
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.gridview.*

class TVAdapter(private val listener: (Long)->Unit): ListAdapter< TV, TVAdapter.ViewHolder>(DiffCallBack()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemLayout=LayoutInflater.from(parent.context).inflate(R.layout.gridview,parent,false)
        return ViewHolder(itemLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class ViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),LayoutContainer {
        init {
            itemView.setOnClickListener {
                listener.invoke(getItem(adapterPosition).id)
            }
        }

        fun bind(tv: TV) {
//            val frontpostero=containerView.findViewById<ImageView>(R.id.frontpostero)
//            val tv_show_name=containerView.findViewById<TextView>(R.id.tv_show_name)
            with(tv) {
                Glide.with(containerView)
                    .load(TmdbService.POSTER_BASE_URL + tv.posterPath)
                    .error(R.drawable.poster_placeholder)
                    .into(frontpostero)
                tv_show_name.text = tv.name
            }
        }
    }
}
class DiffCallBack:DiffUtil.ItemCallback<TV>(){
    override fun areItemsTheSame(oldItem: TV, newItem: TV): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: TV, newItem: TV): Boolean {
        return oldItem==newItem
    }
}
