package com.david.spanisleague.repository

import android.content.Context
import android.util.Log
import com.david.spanisleague.R
import com.david.spanisleague.data.remote.ApiRequest
import com.david.spanisleague.data.local.SoccerLeagueDatabase
import com.david.spanisleague.data.local.TeamDao
import com.david.spanisleague.data.local.TeamResponse
import com.david.spanisleague.data.local.TeamEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * TeamRepository Repository
 *
 * This Class returns the data to the presentation layout
 * a requestTeamReviewList method is created and asks if there is access to the internet performs a callback.enqueue,
 * through retrofit and populates Sqlite Room table and the UI
 *
 * @author david.mazo
 */
class TeamRepository(private val context: Context) {

    private val apiService = ApiRequest.instance
    private val movieDatabase: TeamDao get() = SoccerLeagueDatabase.getSoccerLeague(context).getTeamEventDAO()

    fun requestTeamReviewList(idTeam: String?): List<TeamEvent> {
        apiService.getTeamListFromInternet(idTeam).enqueue(object : Callback<TeamResponse> {
            override fun onResponse(callTeamResponse: Call<TeamResponse>, response: Response<TeamResponse>) {
                when (response.code()) {
                    200 -> {
                        deleteTeamReviewList()
                        insertTeamReviewListIntoDatabase(response)
                    }
                    else -> Log.e(context.getString(R.string.error_tag), context.getString(R.string.error_response_code_different_to_200))
                }
            }

            override fun onFailure(call: Call<TeamResponse>, t: Throwable) {
                Log.e(context.getString(R.string.error_tag), t.printStackTrace().toString())
            }
        })
        return getTeamReviewList()
    }

    private fun insertTeamReviewListIntoDatabase(response: Response<TeamResponse>) {
        if (response.body() != null) {
            for (teamReview: TeamEvent in response.body()!!.events) {
                movieDatabase.insertTeamReview(teamReview)
            }
        }
    }

    fun getTeamReviewList(): List<TeamEvent> {
        return SoccerLeagueDatabase.getSoccerLeague(context).getTeamEventDAO().getTeamReviewList()
    }

    fun deleteTeamReviewList() {
        return SoccerLeagueDatabase.getSoccerLeague(context).getTeamEventDAO().deleteAllTeamEvents()
    }
}
