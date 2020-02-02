package com.david.spanisleague.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.david.spanisleague.R
import com.david.spanisleague.data.remote.ApiRequest
import com.david.spanisleague.data.local.SoccerLeagueDao
import com.david.spanisleague.data.local.SoccerLeagueResponse
import com.david.spanisleague.data.local.SoccerLeague
import com.david.spanisleague.data.local.SoccerLeagueDatabase
import com.david.spanisleague.utils.SPLASH_TIME_OUT
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
class SoccerLeagueRepository() {

    private val apiService = ApiRequest.instance
    //private val soccerLeagueDatabase: SoccerLeagueDao get() = SoccerLeagueDatabase.getSoccerLeague(context).getSoccerLeagueDAO()

    fun requestMovieReviewList(liveData: MutableLiveData<SoccerLeagueResponse>) {
        //TODO cambiar "Spanish La Liga" next line dato quemado
        apiService.getMovieReviewListFromInternet("Spanish La Liga").enqueue(object : Callback<SoccerLeagueResponse> {
            override fun onFailure(call: Call<SoccerLeagueResponse>, t: Throwable) {
                Log.e("tag4545", t.printStackTrace().toString())
            }

            override fun onResponse(call: Call<SoccerLeagueResponse>, response: Response<SoccerLeagueResponse>) {
                liveData.value = response.body()
                insertMovieReviewListIntoDatabase(response)
            }

        })
    }
    /*
    fun requestMovieReviewList(league: String): List<SoccerLeague> {
        apiService.getMovieReviewListFromInternet(league).enqueue(object : Callback<SoccerLeagueResponse> {
            override fun onResponse(callSoccerLeagueResponse: Call<SoccerLeagueResponse>, response: Response<SoccerLeagueResponse>) {
                when (response.code()) {
                    200 -> {
                        GlobalScope.launch(context = Dispatchers.Main) {
                            insertMovieReviewListIntoDatabase(response)
                        }

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
    */

    private fun insertMovieReviewListIntoDatabase(response: Response<SoccerLeagueResponse>) {
        if (response.body() != null) {
            for (soccerLeague: SoccerLeague in response.body()!!.teams) {
                //soccerLeagueDatabase.insertMovieReview(soccerLeague)
            }
        }
    }

    /*
    fun getMovieReviewList(): List<SoccerLeague> {
        return SoccerLeagueDatabase.getSoccerLeague(context).getSoccerLeagueDAO().getMovieReviewList()
    }

    fun deleteMovieReviewList() {
        return SoccerLeagueDatabase.getSoccerLeague(context).getSoccerLeagueDAO().deleteAllSoccerLeague()
    }
    */

}
