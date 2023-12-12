package com.example.mymoviedb.view.trending

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviedb.databinding.ItemTrendingHighlightBinding
import com.example.mymoviedb.model.Result
import com.example.mymoviedb.utils.Const

class TrendingHighlightAdapter(
    private var results: ArrayList<Result>,
    private var listener: OnTrendingHighlightClickListener,
) :
    RecyclerView.Adapter<TrendingHighlightAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemTrendingHighlightBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemTrendingHighlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            results[position].let { result ->
                Glide.with(holder.itemView.context).load(Const.BASE_IMAGE_PATH + result.posterPath)
                    .into(binding.imgTrendingHighlight)
                binding.imgTrendingHighlight.setOnClickListener {
                    listener.onItemHighlightClick(result)
                }
            }
        }
    }

    fun updateData(newResult: ArrayList<Result>) {
        results.clear()
        results.addAll(newResult)
    }
}

interface OnTrendingHighlightClickListener {
    fun onItemHighlightClick(result: Result)
}
