package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.ui.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_tvshow.*
import org.jetbrains.anko.support.v4.startActivity

class TvShowFragment : Fragment() {

    private lateinit var tvShowViewModel: TvShowViewModel

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
            val tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)

            val adapter = TvShowAdapter(tvShowViewModel.getTvShows()) {
                startActivity<DetailActivity>(
                    DetailActivity.ID to it.id,
                    DetailActivity.TYPE to "tv"
                )
            }
            adapter.notifyDataSetChanged()

            rv_tv_show.layoutManager = LinearLayoutManager(context)
            rv_tv_show.adapter = adapter
        }
    }
}