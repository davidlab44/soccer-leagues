package com.david.spanisleague.repository

import android.content.Context
import android.util.Log
import com.david.spanisleague.R
import com.david.spanisleague.api.ApiService
import com.david.spanisleague.model.SoccerLeagueDao
import com.david.spanisleague.model.SoccerLeagueResponse
import com.david.spanisleague.model.SoccerLeague
import com.david.spanisleague.model.SoccerLeagueDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * SoccerLeagueRepository class
 *
 * This Class returns the data to the presentation layout
 * a requestSoccerLeagueList method is created and asks if there is access to the internet performs a callback.enqueue,
 * through retrofit and populates Sqlite Room table and the UI
 *
 * @author david.mazo
 */
class SoccerLeagueRepository(private val context: Context) {

    private val apiService = ApiService.instance
    private val soccerLeagueDatabase: SoccerLeagueDao get() = SoccerLeagueDatabase.getMovieDatabase(context).getMovieDAO()

    fun requestMovieReviewList(league:String): List<SoccerLeague> {
        apiService.getMovieReviewListFromInternet(league).enqueue(object : Callback<SoccerLeagueResponse> {
            override fun onResponse(callSoccerLeagueResponse: Call<SoccerLeagueResponse>, response: Response<SoccerLeagueResponse>) {
                when (response.code()) {
                    200 -> {
                        deleteMovieReviewList()
                        insertMovieReviewListIntoDatabase(response)
                    }
                    else -> Log.e(context.getString(R.string.error_tag), context.getString(R.string.error_response_code_different_to_200))
                }
            }

            override fun onFailure(call: Call<SoccerLeagueResponse>, t: Throwable) {
                Log.e(context.getString(R.string.error_tag), t.printStackTrace().toString())
            }
        })
        return getMovieReviewList()
    }

    private fun insertMovieReviewListIntoDatabase(response: Response<SoccerLeagueResponse>) {
        if (response.body() != null) {
            for (soccerLeague: SoccerLeague in response.body()!!.teams) {
                soccerLeagueDatabase.insertMovieReview(soccerLeague)
            }
        }
    }

    fun getMovieReviewList(): List<SoccerLeague> {
        return SoccerLeagueDatabase.getMovieDatabase(context).getMovieDAO().getMovieReviewList()
    }


    fun deleteMovieReviewList() {
        return SoccerLeagueDatabase.getMovieDatabase(context).getMovieDAO().deleteAllSoccerLeague()
    }
}
