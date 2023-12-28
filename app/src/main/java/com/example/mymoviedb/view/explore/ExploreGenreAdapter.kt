package com.example.mymoviedb.view.explore

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviedb.databinding.ItemExploreGenreBinding
import com.example.mymoviedb.model.GenreRemoteModel
import com.example.mymoviedb.utils.Const
import kotlin.random.Random


class ExploreGenreAdapter(
    private val genres: ArrayList<GenreRemoteModel>,
    private val listener: ExploreGenreClickListener,
) :
    RecyclerView.Adapter<ExploreGenreAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemExploreGenreBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemExploreGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = genres.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            genres[position].let {
                binding.textGenreItem.apply {
                    text = it.name
                    val newBG = background as GradientDrawable
                    background = newBG.apply {
                        setColor(Color.parseColor("#" + Const.colors[Random.nextInt(35)]))
                    }
                    setOnClickListener {
                        listener.onItemGenreClick(it.id)
                    }
                }
            }
        }
    }

    fun updateData(list: ArrayList<GenreRemoteModel>) {
        genres.apply {
            clear()
            addAll(list)
        }
    }
}

interface ExploreGenreClickListener {
    fun onItemGenreClick(id: Int)
}
