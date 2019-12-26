package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

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
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.antoni.wijaya.jetpackpro.ui.detail.DetailActivity
import com.antoni.wijaya.jetpackpro.viewmodel.ViewModelFactory
import com.antoni.wijaya.jetpackpro.vo.Status
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.jetbrains.anko.support.v4.startActivity

class TvShowFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            val tvShowViewModel = obtainViewModel(activity)

            tvShowViewModel?.getTvShows()?.observe(this, Observer { it ->
                run {
                    when (it.status) {
                        Status.ERROR -> {
                            rv_tv_show.visibility = View.GONE
                            progress_bar.visibility = View.VISIBLE
                            Toast.makeText(context, "Failed to get Movie Data", Toast.LENGTH_SHORT)
                                .show()
                        }
                        Status.SUCCESS -> {
                            progress_bar.visibility = View.GONE
                            rv_tv_show.visibility = View.VISIBLE
                            val adapter = TvShowAdapter(it.data as ArrayList<TvShowEntity>) {
                                startActivity<DetailActivity>(
                                    DetailActivity.ID to it.id,
                                    DetailActivity.TYPE to "tvshows"
                                )
                            }
                            adapter.notifyDataSetChanged()
                            rv_tv_show.layoutManager = LinearLayoutManager(context)
                            rv_tv_show.adapter = adapter
                        }
                        Status.LOADING -> {
                            rv_tv_show.visibility = View.GONE
                            progress_bar.visibility = View.VISIBLE
                        }
                    }
                }
            })
        }
    }

    private fun obtainViewModel(activity: FragmentActivity?): TvShowViewModel? {
        val factory = activity?.application?.let { ViewModelFactory.getInstance(it) }

        return activity?.let { ViewModelProviders.of(it, factory).get(TvShowViewModel::class.java) }
    }
}