package com.antoni.wijaya.jetpackpro.ui.detail

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.viewmodel.ViewModelFactory
import com.antoni.wijaya.jetpackpro.vo.Status
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.design.snackbar

class DetailActivity : AppCompatActivity() {


    private var isFav: Boolean = false
    private var menuItem: Menu? = null
    private var type: String = ""
    private var id: String = ""
    private var detailViewModel: DetailViewModel? = null

    companion object {
        const val ID = "id"
        const val TYPE = "type"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val viewModel = obtainViewModel(this)

        id = intent.extras?.get(ID).toString()
        type = intent.extras?.get(TYPE).toString()
        detailViewModel = viewModel
        checkFavorite()
//        addToFav(viewModel, type, id)
        if (viewModel != null) {
            viewModel.id = id
            viewModel.setUsername("Antoni")

        }
//        val movie = viewModel.getSelectedShow(type, id)
        layout.visibility = View.GONE
//
        if (type == "movie") {
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
                        val movie = it.data
//                        favoriteMovieEntity = assignMovieDataToFavorite(movie)

                        if (movie != null) {
                            Glide.with(this)
                                .load(movie.imageUrl)
                                .into(img_poster)

                            txt_title.text = movie.title
                            txt_desc.text = movie.desc
                            txt_date.text = movie.releasedDate
                            txt_genre.text = movie.genres
                            supportActionBar?.title = movie.title
                            rating.rating = movie.rating?.toFloat()?.div(20) ?: 0F
                        }
                        layout.visibility = View.VISIBLE
                        progress_bar.visibility = View.GONE
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
                            supportActionBar?.title = movie.title
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


        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun obtainViewModel(activity: AppCompatActivity): DetailViewModel? {
        val viewModelFactory = ViewModelFactory()
        val factory = viewModelFactory.getInstanceDetail(activity.application)
        return ViewModelProviders.of(activity, factory).get(DetailViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu_favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_add_favorite -> {
                if (isFav) removeFromFavorite()
                else addToFav()

                isFav = !isFav
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFav(
    ) {
        try {
            if (type == "movie")
                detailViewModel?.setMovieFavorite(id)
            else
                detailViewModel?.setTvShowFavorite(id)
            swipe_refresh.snackbar("Added to Favorite").show()
        } catch (e: SQLiteConstraintException) {
            swipe_refresh.snackbar(e.localizedMessage).show()
        }
    }

    //
    private fun checkFavorite() {
        if (type == "movie") {
            detailViewModel?.checkFavoriteMovies(id)?.observe(this, Observer { data ->
                when (data.status) {
                    Status.LOADING -> {
                        showLoading(true)
                    }
                    Status.SUCCESS -> {
                        showLoading(false)
                        if (data?.data?.isFav!!) {
                            isFav = data.data.isFav
                        }
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            this,
                            "Failed to get Favorite Movie Data",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            })
        } else {
            detailViewModel?.checkFavoriteTvShow(id)?.observe(this, Observer { data ->
                when (data.status) {
                    Status.LOADING -> {
                        showLoading(true)
                    }
                    Status.SUCCESS -> {
                        showLoading(false)
                        if (data?.data?.isFav!!) {
                            isFav = data.data.isFav
                        }
                    }
                    Status.ERROR -> {
                        showLoading(true)
                        Toast.makeText(
                            this,
                            "Failed to get Favorite Tv Show Data",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            })
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progress_bar.visibility = View.VISIBLE
            relative_layout.visibility = View.GONE
        } else {
            progress_bar.visibility = View.GONE
            relative_layout.visibility = View.VISIBLE
        }
    }

    //
    private fun setFavorite() {
        if (isFav)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_bookmark_white_24dp)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_bookmark_border_white_24dp)
    }

    //
    private fun removeFromFavorite() {

        try {
            if (type == "movie")
                detailViewModel?.removeMovieFavorite(id)
            else
                detailViewModel?.removeTvShowFavorite(id)
            swipe_refresh.snackbar("Removed from favorite").show()
        } catch (e: SQLiteConstraintException) {
            swipe_refresh.snackbar(e.localizedMessage).show()
        }
    }
}
