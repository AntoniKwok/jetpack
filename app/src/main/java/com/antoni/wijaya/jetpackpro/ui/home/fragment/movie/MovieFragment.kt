package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.ui.detail.DetailActivity
import com.antoni.wijaya.jetpackpro.viewmodel.ViewModelFactory

import org.jetbrains.anko.support.v4.startActivity

class MovieFragment : Fragment() {

    private lateinit var rvMovie : RecyclerView

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

            val adapter = MovieAdapter(movieViewModel!!.getMovies()) {
                startActivity<DetailActivity>(
                    DetailActivity.ID to it.id,
                    DetailActivity.TYPE to "movie"
                )
            }
            adapter.notifyDataSetChanged()

            rvMovie.layoutManager = LinearLayoutManager(context)
            rvMovie.adapter = adapter
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?) : MovieViewModel? {
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }

        return activity?.let { ViewModelProviders.of(it, factory).get(MovieViewModel::class.java) }
    }

}