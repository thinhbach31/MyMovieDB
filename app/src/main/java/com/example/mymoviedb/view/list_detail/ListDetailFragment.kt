package com.example.mymoviedb.view.list_detail

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentListDetailBinding
import com.example.mymoviedb.utils.Const
import com.example.mymoviedb.utils.DataResult
import com.example.mymoviedb.utils.Functions.generateTotalPageSize
import com.example.mymoviedb.utils.Functions.getNameByTitle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListDetailFragment :
    BaseFragment<FragmentListDetailBinding>(FragmentListDetailBinding::inflate) {

    private val viewModel: ListDetailViewModel by viewModels()
    private lateinit var genresAdapter: ListDetailAdapter
    private lateinit var spinnerAdapter: ArrayAdapter<Int>

    override fun observeData() {
        viewModel.filmsLiveData.observe(this) {
            when (it.status) {
                DataResult.Status.SUCCESS -> {
                    Log.d("Status genre frag", "genre success")
                    it.data?.results?.let { res ->
                        genresAdapter.updateData(res)
                        genresAdapter.notifyDataSetChanged()
                    }
                    it.data?.totalPages?.let {
                        spinnerAdapter.apply {
                            clear()
                            addAll(generateTotalPageSize(it))
                            notifyDataSetChanged()
                        }
                    }
                }

                DataResult.Status.ERROR -> {
                    Log.d("Status genre frag", "genre error")
                }
            }
        }
    }

    override fun requestData() {
        viewModel.getFilmsByTitle(1)
    }

    override fun initUIComponents() {
        arguments?.getString(Const.LIST_DETAIL_TAG_TITLE)?.let {title ->
            binding.textTitle.text = context?.let { title.getNameByTitle(it) }
            viewModel.setTitle(title)
        }
        genresAdapter = ListDetailAdapter(arrayListOf(), object : OnGenreListItemClickListener {
            override fun onItemClick(id: Int, mediaType: String) {

            }
        })
        binding.recyclerFilm.apply {
            layoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
            adapter = genresAdapter
            setHasFixedSize(true)
            isNestedScrollingEnabled = false
        }
        binding.btnBack.setOnClickListener {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }

        context?.let {
            spinnerAdapter = ArrayAdapter(
                it, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                arrayListOf<Int>()
            )
        }

        binding.spinnerPaging.adapter = spinnerAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(tagTitle: String) = ListDetailFragment().apply {
            arguments = Bundle().apply {
                putString(Const.LIST_DETAIL_TAG_TITLE, tagTitle)
            }
        }
    }
}
