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
import com.david.spanisleague.utils.TAG_ON_FAILURE
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

    fun requestMovieReviewList(league:String,liveData: MutableLiveData<SoccerLeagueResponse>) {
        apiService.getMovieReviewListFromInternet(league).enqueue(object : Callback<SoccerLeagueResponse> {
            override fun onFailure(call: Call<SoccerLeagueResponse>, t: Throwable) {
                Log.e(TAG_ON_FAILURE, t.printStackTrace().toString())
            }

            override fun onResponse(call: Call<SoccerLeagueResponse>, response: Response<SoccerLeagueResponse>) {
                liveData.value = response.body()
            }

        })
    }
}
