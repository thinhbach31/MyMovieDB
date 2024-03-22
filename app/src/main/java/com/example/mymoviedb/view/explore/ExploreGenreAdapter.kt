package com.example.mymoviedb.view.explore

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviedb.databinding.ItemExploreGenreBinding
import com.example.mymoviedb.model.GenreLocalModel
import com.example.mymoviedb.utils.Const
import kotlin.random.Random


class ExploreGenreAdapter(
    private val genres: ArrayList<GenreLocalModel>,
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
            genres[position].let { genre ->
                binding.textGenreItem.apply {
                    text = genre.name
                    val newBG = background as GradientDrawable
                    background = newBG.apply {
                        Const.colors.let {
                            setColor(Color.parseColor("#" + it[Random.nextInt(it.size - 1)]))
                        }
                    }
                    setOnClickListener {
                        listener.onItemGenreClick(genre)
                    }
                }
            }
        }
    }

    fun updateData(list: ArrayList<GenreLocalModel>) {
        genres.apply {
            clear()
            addAll(list)
        }
    }
}

interface ExploreGenreClickListener {
    fun onItemGenreClick(genre: GenreLocalModel)
}
