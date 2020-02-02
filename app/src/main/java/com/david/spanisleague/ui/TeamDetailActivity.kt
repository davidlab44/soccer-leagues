package com.david.spanisleague.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.david.spanisleague.model.SoccerLeague
import com.david.spanisleague.model.SoccerLeagueDatabase.Companion.getSoccerLeague
import com.david.spanisleague.model.TeamReview
import com.david.spanisleague.repository.TeamRepository
import com.david.spanisleague.utils.ID_SOCCER_LEAGUE
import kotlinx.android.synthetic.main.detail_item.*

/**
 * TeamDetailActivity
 *
 * Show the detail in the UI about a selected soccer team
 *
 * @author david.mazo
 */
class TeamDetailActivity : AppCompatActivity() {

    private lateinit var teamRepository: TeamRepository
    private lateinit var adapter: ArrayAdapter<TeamReview>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.david.spanisleague.R.layout.detail_item)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val idSoccerLeague = getSoccerLeague(this).getSoccerLeagueDAO().getSoccerLeagueDetail(intent.getIntExtra(ID_SOCCER_LEAGUE, 0))
        bindSoccerLeague(idSoccerLeague)
        requestNextFiveEvents(idSoccerLeague)
    }

    private fun requestNextFiveEvents(soccerLeague: SoccerLeague) {
        teamRepository = TeamRepository(this)
        adapter = ArrayAdapter(application.applicationContext, android.R.layout.simple_list_item_1, teamRepository.requestTeamReviewList(soccerLeague.idTeam))
        roadReferenceListView.adapter = adapter
    }

    private fun bindSoccerLeague(soccerLeague: SoccerLeague) {
        textViewStrTeam.text = soccerLeague.strTeam
        textViewStrDescriptionEN.text = soccerLeague.strDescriptionEN
        textViewIntFormedYear.text = "Foundated in ${soccerLeague.intFormedYear}"
        textViewWebsite.text = soccerLeague.strWebsite
        if(soccerLeague.strFacebook!!.isBlank() && soccerLeague.strTwitter!!.isBlank() && soccerLeague.strInstagram!!.isBlank())
            textViewSocialNetwork.visibility = View.INVISIBLE
        textViewFacebook.text = soccerLeague.strFacebook
        textViewTwitter.text = soccerLeague.strTwitter
        textViewInstagram.text = soccerLeague.strInstagram
        Glide.with(imageViewStrTeamBadge)
                .load(soccerLeague.strTeamBadge)
                .centerCrop()
                .fitCenter()
                .override(400, 400)
                .into(imageViewStrTeamBadge)
        Glide.with(imageViewStrTeamJersey)
                .load(soccerLeague.strTeamJersey)
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
            return Intent(context, TeamDetailActivity::class.java)
        }
    }
}
