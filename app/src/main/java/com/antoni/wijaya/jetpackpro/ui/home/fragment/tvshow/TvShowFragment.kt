package com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow

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
import kotlinx.android.synthetic.main.fragment_tvshow.*

class TvShowFragment : Fragment() {

    private lateinit var tvShowViewModel: TvShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tvshow, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if(activity != null){
            val tvShowViewModel = ViewModelProviders.of(this).get(TvShowViewModel::class.java)

            val adapter = TvShowAdapter(tvShowViewModel.getTvShows())
            adapter.notifyDataSetChanged()

            rv_tv_show.layoutManager = LinearLayoutManager(context)
            rv_tv_show.adapter = adapter
        }
    }
}