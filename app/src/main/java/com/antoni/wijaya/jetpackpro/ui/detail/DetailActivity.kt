package com.antoni.wijaya.jetpackpro.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.antoni.wijaya.jetpackpro.R
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

        val viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        val id = intent.extras?.get(ID).toString()
        val type = intent.extras?.get(TYPE).toString()

        val movie = viewModel.getSelectedShow(type, id)

        if (movie != null) {
            Glide.with(this)
                .load(movie.image)
                .into(img_poster)

            txt_title.text = movie.title
            txt_desc.text = movie.desc
            txt_date.text = movie.released_date
            txt_genre.text = movie.genres
            txt_toolbar.text = movie.title
            rating.rating = movie.rating.toFloat() / 20
        }

        btn_back.setOnClickListener {
            finish()
        }
    }
}
