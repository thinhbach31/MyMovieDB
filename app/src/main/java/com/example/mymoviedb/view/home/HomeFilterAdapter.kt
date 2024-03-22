package com.example.mymoviedb.view.home

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mymoviedb.R
import com.example.mymoviedb.databinding.ItemHomeFilterBinding
import com.example.mymoviedb.model.HomeFilter
import com.example.mymoviedb.utils.Functions.hide
import com.example.mymoviedb.utils.Functions.show


class HomeFilterAdapter(
    private val filters: ArrayList<HomeFilter>,
    private val listener: HomeFilterClickListener,
) :
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
                binding.root.setOnClickListener {
                    if (!filter.isSelected) listener.onFilterItemCLick(filter.id)
                }

                if (filter.id == 0) {
                    if (filter.isSelected) {
                        binding.imgHomeFilter.hide()
                        binding.root.hide()
                    } else {
                        binding.imgHomeFilter.show()
                        binding.root.show()
                    }
                    return
                }

                val drawable = binding.roundedBackground.background as GradientDrawable
                drawable.mutate()

                if (filter.isSelected) {
                    setSelectedFilter(
                        drawable, holder.itemView.context,
                        binding.titleItemHomeFilter
                    )
                } else {
                    setUnselectedFilter(
                        drawable, holder.itemView.context,
                        binding.titleItemHomeFilter
                    )
                }
                binding.root.show()
                binding.titleItemHomeFilter.apply {
                    text = filter.name
                    show()
                }
            }
        }
    }

    private fun setSelectedFilter(
        drawable: GradientDrawable, context: Context, textView: TextView,
    ) {
        drawable.setStroke(
            2, ResourcesCompat.getColor(context.resources, R.color.white, null)
        )
        textView.setTextColor(context.resources.getColor(R.color.white, null))
    }

    private fun setUnselectedFilter(
        drawable: GradientDrawable, context: Context, textView: TextView,
    ) {
        drawable.setStroke(
            1, context.resources.getColor(R.color.color_grey_home_filter, null)
        )
        textView.setTextColor(context.resources.getColor(R.color.color_grey_home_filter, null))
    }

    fun updateFilterSelectedStatus(id: Int) {
        filters.forEach {
            it.isSelected = it.id == id
        }
    }
}

interface HomeFilterClickListener {
    fun onFilterItemCLick(id: Int)
}
