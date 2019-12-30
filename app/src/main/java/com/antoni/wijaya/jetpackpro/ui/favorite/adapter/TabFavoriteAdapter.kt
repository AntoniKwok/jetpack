package com.antoni.wijaya.jetpackpro.ui.favorite.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.antoni.wijaya.jetpackpro.ui.favorite.fragment.movie.FavoriteMovieFragment
import com.antoni.wijaya.jetpackpro.ui.favorite.fragment.tvshow.FavoriteTvShowFragment

class TabFavoriteAdapter(
    fm: FragmentManager
) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                val frg =
                    FavoriteMovieFragment.newInstance()
                frg
            }
            else -> {
                val frg =
                    FavoriteTvShowFragment.newInstance()
                frg
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Movie"
            else -> "Tv Show"
        }
    }

}