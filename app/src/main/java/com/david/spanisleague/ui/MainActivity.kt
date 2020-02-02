package com.david.spanisleague.ui

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.david.spanisleague.R
import com.david.spanisleague.data.local.SoccerLeague
import com.david.spanisleague.data.local.SoccerLeagueDao
import com.david.spanisleague.data.local.SoccerLeagueDatabase
import com.david.spanisleague.repository.SoccerLeagueRepository
import com.david.spanisleague.utils.ID_SOCCER_LEAGUE
import com.google.android.material.snackbar.Snackbar.LENGTH_LONG
import com.google.android.material.snackbar.Snackbar.make
import kotlinx.android.synthetic.main.activity_main.constraintLayoutMainActivity
import kotlinx.android.synthetic.main.activity_main.recyclerView

class MainActivity : AppCompatActivity(), SoccerLeagueEvents {

    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var soccerLeagueRepository: SoccerLeagueRepository
    private lateinit var soccerLeagueListAdapter: SoccerLeagueListAdapter

    private val myViewModel by lazy {
        return@lazy ViewModelProviders.of(this).get(MyViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchDialogFragment(R.string.app_name, R.drawable.soccer_leagues)
        myViewModel.callApi("Spanish La Liga")
        observeResponseData()
    }

    private fun observeResponseData() {
        myViewModel.liveData.observe(this, Observer { data ->
            if (hasConnection()) {
                SoccerLeagueDatabase.getSoccerLeague(applicationContext).getSoccerLeagueDAO().deleteAllSoccerLeague()
                val datos = data
                Log.e("tag219","mesage"+data)
                val soccerLeagueDatabase: SoccerLeagueDao = SoccerLeagueDatabase.getSoccerLeague(application.applicationContext).getSoccerLeagueDAO()
                for (soccerLeague: SoccerLeague in datos.teams) {
                    soccerLeagueDatabase.insertMovieReview(soccerLeague)
                }
                soccerLeagueListAdapter = SoccerLeagueListAdapter(this)
                gridLayoutManager = GridLayoutManager(this, 2)
                recyclerView.layoutManager = gridLayoutManager
                recyclerView.adapter = soccerLeagueListAdapter
                soccerLeagueRepository = SoccerLeagueRepository()
                soccerLeagueListAdapter.addAll(SoccerLeagueDatabase.getSoccerLeague(application.applicationContext).getSoccerLeagueDAO().getMovieReviewList())
            }else{
                soccerLeagueListAdapter = SoccerLeagueListAdapter(this)
                gridLayoutManager = GridLayoutManager(this, 2)
                recyclerView.layoutManager = gridLayoutManager
                recyclerView.adapter = soccerLeagueListAdapter
                soccerLeagueRepository = SoccerLeagueRepository()
                soccerLeagueListAdapter.addAll(SoccerLeagueDatabase.getSoccerLeague(application.applicationContext).getSoccerLeagueDAO().getMovieReviewList())
            }
        })
    }

    private fun launchDialogFragment(name: Int, image: Int) {
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.spanish_league -> {
                myViewModel.callApi("Spanish La Liga")
                return true
            }
            R.id.german_bundesliga -> {
                myViewModel.callApi("German Bundesliga")
                return true
            }
            R.id.portuguese_primeira_liga -> {
                myViewModel.callApi("Portuguese Primeira Liga")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onItemClicked(soccerLeague: SoccerLeague) {
        val intent: Intent = TeamDetailActivity.createIntent(this@MainActivity)
        intent.putExtra(ID_SOCCER_LEAGUE, soccerLeague.id)
        startActivity(intent)
    }

    private fun hasConnection(): Boolean {
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}
