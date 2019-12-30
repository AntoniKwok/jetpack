package com.antoni.wijaya.jetpackpro.ui.home.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.ui.favorite.FavoriteActivity
import com.antoni.wijaya.jetpackpro.ui.home.fragment.movie.MovieFragment
import com.antoni.wijaya.jetpackpro.ui.home.fragment.tvshow.TvShowFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.jetbrains.anko.startActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        if (savedInstanceState == null)
            loadFragment(MovieFragment())
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_movie -> loadFragment(MovieFragment())
                R.id.navigation_tvshow -> loadFragment(TvShowFragment())
            }
            true
        }

    private fun loadFragment(fragment: Fragment?): Boolean {
        if (fragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frag_container, fragment)
                .commit()
            return true
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu_favorite, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.btn_favorite_menu -> {
                startActivity<FavoriteActivity>()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}
