package com.david.spanisleague.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.david.spanisleague.R
import com.david.spanisleague.model.MovieReview
import com.david.spanisleague.repository.MovieRepository
import com.david.spanisleague.utils.GRILL_LAYOUT
import com.david.spanisleague.utils.ID_MOVIE
import com.david.spanisleague.utils.LINEAR_LAYOUT
import com.david.spanisleague.utils.STAGGERED_LAYOUT
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar.make
import kotlinx.android.synthetic.main.activity_main.constraintLayoutMainActivity
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity() {


    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var movieRepository: MovieRepository
    private var layoutState: Int = LINEAR_LAYOUT

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //initComponents()
        movieRepository = MovieRepository(this)
        movieRepository.requestMovieReviewList()
    }


}
