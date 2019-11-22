package com.antoni.wijaya.jetpackpro.ui.home.fragment.movie

import android.graphics.Movie
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.utils.DataDummy
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_movie, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(activity != null){
            val movieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

            val adapter = MovieAdapter(movieViewModel.getMovies())
            adapter.notifyDataSetChanged()

            rv_movie.layoutManager = LinearLayoutManager(context)
            rv_movie.adapter = adapter
        }
    }

}