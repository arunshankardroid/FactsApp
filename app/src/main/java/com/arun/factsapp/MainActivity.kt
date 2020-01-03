package com.arun.factsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arun.factsapp.ui.main.MainFragment
import com.arun.factsapp.ui.main.TitleUpdater
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity(), TitleUpdater {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        configureToolbar()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    private fun configureToolbar() {
        toolbar?.apply {
            setSupportActionBar(this)
        }
    }

    override fun updateTitle(title: String) {
        toolbar.title = title
    }

}
