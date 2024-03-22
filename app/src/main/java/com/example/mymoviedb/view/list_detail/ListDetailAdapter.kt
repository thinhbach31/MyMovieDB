package com.example.mymoviedb.view.list_detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviedb.databinding.ItemListDetailBinding
import com.example.mymoviedb.model.Result
import com.example.mymoviedb.utils.Const

class ListDetailAdapter(
    private val results: ArrayList<Result>,
    private val listener: OnGenreListItemClickListener,
) :
    RecyclerView.Adapter<ListDetailAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemListDetailBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = results[position]
        with(holder) {
            binding.apply {
                Glide.with(itemView.context).load(Const.BASE_IMAGE_PATH + item.posterPath)
                    .into(imgFilmBasic)
                itemView.setOnClickListener {
                    listener.onItemClick(item.id, item.mediaType.orEmpty())
                }
            }
        }
    }

    fun updateData(list: ArrayList<Result>) {
        results.apply {
            clear()
            addAll(list)
        }
    }
}

interface OnGenreListItemClickListener {
    fun onItemClick(id: Int, mediaType: String)
}