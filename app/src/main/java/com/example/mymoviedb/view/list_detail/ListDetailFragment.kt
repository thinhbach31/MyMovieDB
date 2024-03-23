package com.example.mymoviedb.view.list_detail

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mymoviedb.base.BaseFragment
import com.example.mymoviedb.databinding.FragmentListDetailBinding
import com.example.mymoviedb.model.GenreLocalModel
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
        viewModel.getDataListDetail(Const.DEFAULT_RESULT_PAGE)
    }

    override fun initUIComponents() {
        context?.let { context ->
            arguments?.apply {
                viewModel.title = when {
                    !getString(Const.LIST_DETAIL_TAG_TITLE).isNullOrEmpty() -> {
                        viewModel.isFromGenreOrFilmTag = false
                        getString(Const.LIST_DETAIL_TAG_TITLE)!!.getNameByTitle(context)
                    }
                    !getString(Const.GENRE_NAME).isNullOrEmpty() -> {
                        viewModel.apply {
                            isFromGenreOrFilmTag = true
                            isMovieGenreOrTV = getBoolean(Const.IS_MOVIE_GENRE)
                            genreId = getInt(Const.GENRE_ID)
                        }
                        getString(Const.GENRE_NAME)!!
                    }
                    else -> ""
                }
            }
        }
        binding.textTitle.text = viewModel.title

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
                it,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
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

        fun newInstance(isMovieGenre: Boolean, genre: GenreLocalModel) =
            ListDetailFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(Const.IS_MOVIE_GENRE, isMovieGenre)
                    putInt(Const.GENRE_ID, genre.id)
                    putString(Const.GENRE_NAME, genre.name)
                }
            }
    }
}
