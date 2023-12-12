package com.example.mymoviedb.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mymoviedb.databinding.ItemFilmBasicBinding
import com.example.mymoviedb.model.Result
import com.example.mymoviedb.utils.Const

class BasicFilmAdapter(
    private var results: ArrayList<Result>,
    private val listener: OnBasicItemListener,
) :
    RecyclerView.Adapter<BasicFilmAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemFilmBasicBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemFilmBasicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            results[position].let { result ->
                Glide.with(holder.itemView.context).load(Const.BASE_IMAGE_PATH + result.posterPath)
                    .into(binding.imgFilmBasic)
                binding.imgFilmBasic.setOnClickListener {
                    listener.onBasicItemClick(result)
                }
            }
        }
    }

    fun updateData(newResult: ArrayList<Result>) {
        results.clear()
        results.addAll(newResult)
    }
}

interface OnBasicItemListener {
    fun onBasicItemClick(result: Result)
}
