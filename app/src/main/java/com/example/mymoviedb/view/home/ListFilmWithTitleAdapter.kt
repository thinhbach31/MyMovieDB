package com.example.mymoviedb.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviedb.databinding.ItemListFilmWithTitleBinding
import com.example.mymoviedb.model.ListFilmWithTitle
import com.example.mymoviedb.model.Result
import com.example.mymoviedb.utils.Functions.getNameByTitle

class ListFilmWithTitleAdapter(
    private var films: ArrayList<ListFilmWithTitle>,
    private val listener: OnListFilmWithTitleClickListener,
) :
    RecyclerView.Adapter<ListFilmWithTitleAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemListFilmWithTitleBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemListFilmWithTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = films.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            films[position].let { film ->
                binding.textTitleListFilm.apply {
                    text = film.title.getNameByTitle(this.context)
//                    paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
                }
                binding.layoutTitleFilm.setOnClickListener {
                    listener.onTitleClickListener(film.title)
                }
                binding.recyclerListFilm.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = BasicFilmAdapter(film.results, object : OnBasicItemListener {
                        override fun onBasicItemClick(result: Result) {
                            listener.onFilmItemClickListener(result)
                        }
                    })
                    setHasFixedSize(true)
                }
            }
        }
    }

    fun addItem(film: ListFilmWithTitle) {
        films.add(film)
    }
}

interface OnListFilmWithTitleClickListener {
    fun onTitleClickListener(title: String)
    fun onFilmItemClickListener(result: Result)
}
