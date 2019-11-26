package com.antoni.wijaya.testing

import android.os.Bundle
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.antoni.wijaya.jetpackpro.R

class SingleFragmentActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = FrameLayout(this)
        val params = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.MATCH_PARENT,
            Gravity.CENTER
        )
        layout.layoutParams = params
        layout.id = R.id.frag_container

        setContentView(layout)
    }

    fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.frag_container, fragment, "TEST")
            .commit()
    }


}