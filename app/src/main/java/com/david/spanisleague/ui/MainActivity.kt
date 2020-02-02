package com.david.spanisleague.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.david.spanisleague.R
import com.david.spanisleague.model.SoccerLeague
import com.david.spanisleague.model.SoccerLeagueDatabase
import com.david.spanisleague.repository.SoccerLeagueRepository
import com.david.spanisleague.utils.ID_MOVIE
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar.make
import kotlinx.android.synthetic.main.activity_main.constraintLayoutMainActivity
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity(), SoccerLeagueEvents {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var staggeredGridLayoutManager: StaggeredGridLayoutManager
    private lateinit var soccerLeagueRepository: SoccerLeagueRepository
    private lateinit var soccerLeagueListAdapter: SoccerLeagueListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerViewSoccerLeagues("Spanish La Liga")
        launchDialogFragment(R.string.app_name,R.drawable.soccer_leagues)
        val idMovieReview = SoccerLeagueDatabase.getMovieDatabase(this).getMovieDAO().getMovieReviewDetail(intent.getIntExtra(ID_MOVIE, 0))

        val idMovieReview2 = SoccerLeagueDatabase.getMovieDatabase(this).getMovieDAO().getMovieReviewDetail(intent.getIntExtra(ID_MOVIE, 0))


    }

    private fun launchDialogFragment(name: Int,image:Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        val prev = supportFragmentManager.findFragmentByTag("dialog")
        if (prev != null) {
            fragmentTransaction.remove(prev)
        }
        fragmentTransaction.addToBackStack(null)
        val dialogFragment = SplashViewDialogFragment(application)
        val dialogFragmentBundle = Bundle()
        dialogFragmentBundle.putInt("name", name)
        dialogFragmentBundle.putInt("image", image)
        dialogFragment.arguments = dialogFragmentBundle
        dialogFragment.show(fragmentTransaction, "dialog")
    }

    private fun setRecyclerViewSoccerLeagues(league:String) {
        soccerLeagueListAdapter = SoccerLeagueListAdapter(this)
        gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        staggeredGridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = gridLayoutManager
        recyclerView.adapter = soccerLeagueListAdapter
        soccerLeagueRepository = SoccerLeagueRepository(this)
        if (hasConnection()) {
            soccerLeagueListAdapter.addAll(soccerLeagueRepository.requestMovieReviewList(league))
            make(constraintLayoutMainActivity, getString(R.string.movie_review_database_updated), LENGTH_LONG).show()
        } else {
            soccerLeagueListAdapter.addAll(soccerLeagueRepository.getMovieReviewList())
            make(constraintLayoutMainActivity, getString(R.string.not_network_connection), LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.spanish_league ->{
                setRecyclerViewSoccerLeagues("Spanish La Liga")
                return true
            }
            R.id.german_bundesliga ->{
                setRecyclerViewSoccerLeagues("German Bundesliga")
                return true
            }
            R.id.portuguese_primeira_liga ->{
                setRecyclerViewSoccerLeagues("Portuguese Primeira Liga")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClicked(soccerLeague: SoccerLeague) {
        val intent: Intent = TeamDetailActivity.createIntent(this@MainActivity)
        intent.putExtra(ID_MOVIE, soccerLeague.id)
        startActivity(intent)
    }

    private fun hasConnection(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
