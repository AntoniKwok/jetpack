package com.antoni.wijaya.jetpackpro.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.ui.favorite.adapter.TabFavoriteAdapter
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        val fragmentAdapter = TabFavoriteAdapter(supportFragmentManager)
        view_pager.adapter = fragmentAdapter
        tabs_type.setupWithViewPager(view_pager)


        supportActionBar?.title = "Favorite"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
