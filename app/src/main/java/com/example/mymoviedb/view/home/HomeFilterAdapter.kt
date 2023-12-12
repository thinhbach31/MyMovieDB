package com.example.mymoviedb.view.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviedb.databinding.ItemHomeFilterBinding
import com.example.mymoviedb.model.HomeFilter

class HomeFilterAdapter(private val filters: ArrayList<HomeFilter>) :
    RecyclerView.Adapter<HomeFilterAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemHomeFilterBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHomeFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = filters.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder) {
            filters[position].let { filter ->
                binding.titleItemHomeFilter.text = filter.name
            }
        }
    }
}

interface HomeFilterClickListener{
    fun onFilterItemCLick(id: Int)
}
