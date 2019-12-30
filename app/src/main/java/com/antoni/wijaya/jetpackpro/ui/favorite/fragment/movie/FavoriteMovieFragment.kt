package com.antoni.wijaya.jetpackpro.ui.favorite.fragment.movie


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.antoni.wijaya.jetpackpro.R

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment() {

    companion object {
        fun newInstance(): FavoriteMovieFragment {
            return FavoriteMovieFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }


}
