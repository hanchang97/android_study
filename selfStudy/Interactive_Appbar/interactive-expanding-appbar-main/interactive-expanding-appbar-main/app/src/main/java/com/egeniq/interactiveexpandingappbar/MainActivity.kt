package com.egeniq.interactiveexpandingappbar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import com.egeniq.interactiveexpandingappbar.binding.BindingActivity
import com.egeniq.interactiveexpandingappbar.databinding.ActivityGenreBinding
import com.egeniq.interactiveexpandingappbar.databinding.ActivityMainBinding
import com.egeniq.interactiveexpandingappbar.model.Genre
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BindingActivity<ActivityMainBinding>() {

    override val layout = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragment = GenreFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).commit()
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        val genreFragment = supportFragmentManager.findFragmentById(binding.fragmentContainer.id) as? GenreFragment ?: return
        val toolbar = genreFragment.getToolbar()
        toolbar.setNavigationIcon(R.drawable.ic_back)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setHomeButtonEnabled(false)
            it.setDisplayShowTitleEnabled(false)
            it.setDisplayHomeAsUpEnabled(false)
        }
        // We have a layout issue here: if the text is in the same row as the toolbar, we want the toolbar back button to take precedence.
        // Normally this would work if we place the toolbar on top, but the toolbar has a weird implementation that it 'eats' all touch events which are not directed to one of its children.
        // But if we place the text on top, it will consume the touch from the back button, because it overlaps that part.
        // So we place the toolbar on the bottom, the text on top of it, and on the top of that a touch forwarder, which is at the place where the back button is always.
        // When the touch forwarder is touched, it will forward the touch to the toolbar, which is below it, so triggering the touch of the back button.
        genreFragment.getTouchForwarder().setOnTouchListener { _, event ->
            return@setOnTouchListener toolbar.dispatchTouchEvent(event)
        }
    }
}