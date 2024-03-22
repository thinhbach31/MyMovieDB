package com.example.mymoviedb.view.list_genre_detail

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
import com.example.mymoviedb.view.list_detail.ListDetailAdapter
import com.example.mymoviedb.view.list_detail.OnGenreListItemClickListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GenreListDetailFragment :
    BaseFragment<FragmentListDetailBinding>(FragmentListDetailBinding::inflate) {

    private lateinit var genresAdapter: ListDetailAdapter
    private lateinit var spinnerAdapter: ArrayAdapter<Int>
    private val viewModel: GenreListDetailViewModel by viewModels()
    override fun observeData() {
        viewModel.movieByGenre.observe(this) {
            when (it?.status) {
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

                else -> {}
            }
        }
    }

    override fun requestData() {
        arguments?.getInt(Const.GENRE_ID)?.let { id ->
            arguments?.getBoolean(Const.IS_MOVIE_GENRE)?.let { isMovie ->
                viewModel.getMovieByGenre(isMovie, id)
            }
        }
    }

    override fun initUIComponents() {
        arguments?.getString(Const.GENRE_NAME)?.let {
            binding.textTitle.text = it
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
                it,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                arrayListOf<Int>()
            )
        }

        binding.spinnerPaging.adapter = spinnerAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(isMovieGenre: Boolean, genre: GenreLocalModel) =
            GenreListDetailFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(Const.IS_MOVIE_GENRE, isMovieGenre)
                    putInt(Const.GENRE_ID, genre.id)
                    putString(Const.GENRE_NAME, genre.name)
                }
            }
    }
}
