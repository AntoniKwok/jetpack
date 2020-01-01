package com.antoni.wijaya.jetpackpro.ui.favorite.fragment.movie


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.ui.detail.DetailActivity
import com.antoni.wijaya.jetpackpro.viewmodel.ViewModelFactory
import com.antoni.wijaya.jetpackpro.vo.Status
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment() {

    private lateinit var adapter: FavoriteMovieAdapter

    @Inject
    var factory: ViewModelProvider.Factory? = null

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        txt_no_data.visibility = View.GONE
        rv_movie.layoutManager = LinearLayoutManager(context)
        if (activity != null) {
            val movieViewModel = obtainViewModel(activity)
            movieViewModel?.getFavoriteMovies()?.removeObservers(this)
            movieViewModel?.getFavoriteMovies()?.observe(this, Observer {
                when (it.status) {
                    Status.ERROR -> {
                        Toast.makeText(context, "Failed to get Movie Data", Toast.LENGTH_SHORT)
                            .show()
                    }
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        if (it.data?.size == 0)
                            txt_no_data.visibility = View.VISIBLE
                        else
                            txt_no_data.visibility = View.GONE
                        adapter = FavoriteMovieAdapter(it.data!!) {
                            startActivity<DetailActivity>(
                                DetailActivity.ID to it.id,
                                DetailActivity.TYPE to "movie"
                            )
                        }
                        adapter.submitList(it.data)
                        rv_movie.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): FavoriteMovieViewModel? {
        factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let {
            ViewModelProviders.of(it, factory).get(FavoriteMovieViewModel::class.java)
        }
    }


}
