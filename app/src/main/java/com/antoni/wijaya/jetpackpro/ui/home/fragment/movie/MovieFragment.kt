package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.data.source.local.entity.MovieEntity
import com.antoni.wijaya.jetpackpro.ui.detail.DetailActivity
import com.antoni.wijaya.jetpackpro.viewmodel.ViewModelFactory
import com.antoni.wijaya.jetpackpro.vo.Status
import kotlinx.android.synthetic.main.fragment_movie.*

import org.jetbrains.anko.support.v4.startActivity

class MovieFragment : Fragment() {

    private lateinit var rvMovie: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvMovie = view.findViewById(R.id.rv_movie)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val movieViewModel = obtainViewModel(activity)

            movieViewModel?.getMovies()?.observe(this, Observer { it ->
                run {
                    when (it.status) {
                        Status.ERROR -> {
                            Toast.makeText(context, "Failed to get Movie Data", Toast.LENGTH_SHORT)
                                .show()
                        }
                        Status.SUCCESS -> {
                            progress_bar.visibility = View.GONE
                            val adapter = MovieAdapter(it.data as ArrayList<MovieEntity>) {
                                startActivity<DetailActivity>(
                                    DetailActivity.ID to it.id,
                                    DetailActivity.TYPE to "movie"
                                )
                            }
                            adapter.notifyDataSetChanged()
                            rvMovie.layoutManager = LinearLayoutManager(context)
                            rvMovie.adapter = adapter
                        }
                        Status.LOADING -> {
                            progress_bar.visibility = View.VISIBLE
                        }
                    }
                }
            })


        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): MovieViewModel? {
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let { ViewModelProviders.of(it, factory).get(MovieViewModel::class.java) }
    }

}