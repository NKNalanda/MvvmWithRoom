package com.nknalanda.mvvmwithroom.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.nknalanda.mvvmwithroom.R
import com.nknalanda.mvvmwithroom.data.database.MovieDatabase
import com.nknalanda.mvvmwithroom.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    private var binding: ActivityMovieBinding? = null
    private var db: MovieDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        showFragment(AddMovieFragment.newInstance("", ""))
        db = MovieDatabase.getAppDatabase(this)
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fl_movie_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.fl_movie_container)
        if (fragment is AddMovieFragment) {
            this.onBackPressed()
        }
    }
}