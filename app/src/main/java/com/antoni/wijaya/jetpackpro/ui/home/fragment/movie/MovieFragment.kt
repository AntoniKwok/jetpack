package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.ui.detail.DetailActivity

import kotlinx.android.synthetic.main.fragment_movie.*
import org.jetbrains.anko.support.v4.startActivity

class MovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (activity != null) {
            val movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

            val adapter = MovieAdapter(movieViewModel.getMovies()) {
                startActivity<DetailActivity>(
                    DetailActivity.ID to it.id,
                    DetailActivity.TYPE to "movie"
                )
            }
            adapter.notifyDataSetChanged()

            rv_movie.layoutManager = LinearLayoutManager(context)
            rv_movie.adapter = adapter
        }
    }

}