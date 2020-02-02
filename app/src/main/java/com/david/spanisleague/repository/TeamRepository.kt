package com.david.spanisleague.repository

import android.content.Context
import android.util.Log
import com.david.spanisleague.R
import com.david.spanisleague.api.ApiService
import com.david.spanisleague.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Team Events Repository
 *
 * This Class returns the data to the MainActivity
 * a requestTeamReviewList method is created and asks if there is access to the internet performs a callback.enqueue,
 * through retrofit and populates the bd implemented with room
 *
 * @author david.mazo
 */
class TeamRepository(private val context: Context) {

    private val apiService = ApiService.instance
    private val movieDatabase: TeamDao get() = MovieReviewDatabase.getMovieDatabase(context).getTeamEventDAO()

    fun requestTeamReviewList(): List<TeamReview> {
        apiService.getTeamListFromInternet().enqueue(object : Callback<TeamResponse> {
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
            for (teamReview: TeamReview in response.body()!!.events) {
                movieDatabase.insertTeamReview(teamReview)
            }
        }
    }

    fun getTeamReviewList(): List<TeamReview> {
        return MovieReviewDatabase.getMovieDatabase(context).getTeamEventDAO().getTeamReviewList()
    }

    fun deleteTeamReviewList() {
        return MovieReviewDatabase.getMovieDatabase(context).getTeamEventDAO().deleteAllTeamEvents()
    }
}