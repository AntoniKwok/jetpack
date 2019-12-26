package com.antoni.wijaya.jetpackpro.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.viewmodel.ViewModelFactory
import com.antoni.wijaya.jetpackpro.vo.Status
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val ID = "id"
        const val TYPE = "type"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val viewModel = obtainViewModel(this)
        val id = intent.extras?.get(ID).toString()
        val type = intent.extras?.get(TYPE).toString()

        if (viewModel != null) {
            viewModel.id = id
            viewModel.setUsername("Antoni")
        }
//        val movie = viewModel.getSelectedShow(type, id)
        layout.visibility = View.GONE
//
        if (type == "movie") {
            Log.wtf("Test", "Woi")
            viewModel?.getMovie()?.observe(this, Observer {

                when (it.status) {
                    Status.ERROR -> {
                        layout.visibility = View.GONE
                        Toast.makeText(
                            this@DetailActivity,
                            "Failed to get data",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    Status.SUCCESS -> {

                        layout.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        val movie = it.data

                        if (movie != null) {
                            Glide.with(this)
                                .load(movie.imageUrl)
                                .into(img_poster)

                            txt_title.text = movie.title
                            txt_desc.text = movie.desc
                            txt_date.text = movie.releasedDate
                            txt_genre.text = movie.genres
                            txt_toolbar.text = movie.title
                            rating.rating = movie.rating?.toFloat()?.div(20) ?: 0F
                        }
                    }
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                        layout.visibility = View.GONE
                    }
                }
            })
        } else {
            viewModel?.getTvShow()?.observe(this, Observer {
                when (it.status) {
                    Status.ERROR -> {
                        layout.visibility = View.GONE
                        Toast.makeText(
                            this@DetailActivity,
                            "Failed to get data",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                    Status.SUCCESS -> {
                        layout.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
                        val movie = it.data

                        if (movie != null) {
                            Glide.with(this)
                                .load(movie.imageUrl)
                                .into(img_poster)

                            txt_title.text = movie.title
                            txt_desc.text = movie.desc
                            txt_date.text = movie.releasedDate
                            txt_genre.text = movie.genres
                            txt_toolbar.text = movie.title
                            rating.rating = movie.rating?.toFloat()?.div(20) ?: 0F
                        }
                    }
                    Status.LOADING -> {
                        progress_bar.visibility = View.VISIBLE
                        layout.visibility = View.GONE
                    }
                }
            })
        }



        btn_back.setOnClickListener {
            finish()
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel? {
        val factory = ViewModelFactory.getInstanceDetail(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailViewModel::class.java)
    }
}
