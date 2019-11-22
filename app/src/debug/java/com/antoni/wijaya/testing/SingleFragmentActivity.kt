package com.antoni.wijaya.testing

import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.antoni.wijaya.jetpackpro.R
import kotlinx.android.synthetic.main.activity_home.view.*

class SingleFragmentActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        val layout = FrameLayout(this)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT, Gravity.CENTER
        )
        layout.layoutParams = params
        layout.id = R.id.container

        setContentView(layout)

    }

    fun setFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().add(R.id.container, fragment, "TEST").commit()
    }

    fun replaceFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().add(R.id.container, fragment).commit()
    }

}