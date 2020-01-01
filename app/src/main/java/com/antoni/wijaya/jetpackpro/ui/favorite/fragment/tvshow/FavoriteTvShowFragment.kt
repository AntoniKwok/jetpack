package com.antoni.wijaya.jetpackpro.ui.favorite.fragment.tvshow


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
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import org.jetbrains.anko.support.v4.startActivity
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTvShowFragment : Fragment() {

    companion object {
        fun newInstance(): FavoriteTvShowFragment {
            return FavoriteTvShowFragment()
        }
    }

    private lateinit var adapter: FavoriteTvShowAdapter

    @Inject
    var factory: ViewModelProvider.Factory? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        txt_no_data.visibility = View.GONE
        rv_tv_show.layoutManager = LinearLayoutManager(context)
        if (activity != null) {
            val movieViewModel = obtainViewModel(activity)
            movieViewModel?.getFavoriteTvShows()?.removeObservers(this)
            movieViewModel?.getFavoriteTvShows()?.observe(this, Observer {
                when (it.status) {
                    Status.ERROR -> {
                        Toast.makeText(context, "Failed to get Tv Show Data", Toast.LENGTH_SHORT)
                            .show()
                    }
                    Status.SUCCESS -> {
                        progress_bar.visibility = View.GONE
                        if (it.data?.size == 0)
                            txt_no_data.visibility = View.VISIBLE
                        else
                            txt_no_data.visibility = View.GONE
                        adapter = FavoriteTvShowAdapter(it.data!!) {
                            startActivity<DetailActivity>(
                                DetailActivity.ID to it.id,
                                DetailActivity.TYPE to "tvshow"
                            )
                        }
                        adapter.submitList(it.data)
                        rv_tv_show.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                    }
                }
            })
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): FavoriteTvShowViewModel? {
        factory = activity?.application?.let { ViewModelFactory.getInstance(it) }
        return activity?.let {
            ViewModelProviders.of(it, factory).get(FavoriteTvShowViewModel::class.java)
        }
    }
}
