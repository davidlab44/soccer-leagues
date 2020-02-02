package com.david.spanisleague.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.david.spanisleague.model.MovieReview
import com.david.spanisleague.model.MovieReviewDatabase.Companion.getMovieDatabase
import com.david.spanisleague.model.TeamReview
import com.david.spanisleague.repository.TeamRepository
import com.david.spanisleague.utils.ID_MOVIE
import kotlinx.android.synthetic.main.detail_item.*

/**
 * TeamDetail
 *
 * Show the detail of a selected movie in the recyclerView of the MainActivity
 *
 * @author juan.rendon
 */
class TeamDetail : AppCompatActivity() {

    private lateinit var teamRepository: TeamRepository
    private lateinit var adapter: ArrayAdapter<TeamReview>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.david.spanisleague.R.layout.detail_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val idMovieReview = getMovieDatabase(this).getMovieDAO().getMovieReviewDetail(intent.getIntExtra(ID_MOVIE, 0))
        bindMovieReview(idMovieReview)
        requestNextFiveEvents()
    }

    private fun requestNextFiveEvents() {
        teamRepository = TeamRepository(this)
        teamRepository.requestTeamReviewList()
        teamRepository.getTeamReviewList()
        Log.e("TeamEvents remoto", "aqui: " + teamRepository.requestTeamReviewList())
        Log.e("TeamEvents local", "aqui: " + teamRepository.getTeamReviewList())
        Log.e("TeamEvents local2", "aqui: " + teamRepository.getTeamReviewList())
        adapter = ArrayAdapter(application.applicationContext, android.R.layout.simple_list_item_1, teamRepository.requestTeamReviewList())
        roadReferenceListView.adapter = adapter
    }

    private fun bindMovieReview(movieReview: MovieReview) {
        textViewStrTeam.text = movieReview.strTeam
        textViewStrDescriptionEN.text = movieReview.strDescriptionEN
        textViewIntFormedYear.text = movieReview.intFormedYear
        text_view_website.text = movieReview.strWebsite
        text_view_facebook.text = movieReview.strFacebook
        text_view_twitter.text = movieReview.strTwitter
        text_view_instagram.text = movieReview.strInstagram
        Glide.with(imageViewStrTeamBadge)
                .load(movieReview.strTeamBadge)
                .centerCrop()
                .fitCenter()
                .override(400, 400)
                .into(imageViewStrTeamBadge)
        Glide.with(imageViewStrTeamJersey)
                .load(movieReview.strTeamJersey)
                .centerCrop()
                .fitCenter()
                .override(400, 400)
                .into(imageViewStrTeamJersey)
    }

    override fun onOptionsItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(menuItem)
    }

    companion object {
        fun createIntent(context: Context): Intent {
            return Intent(context, TeamDetail::class.java)
        }
    }
}
