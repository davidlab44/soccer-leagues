package com.david.spanisleague.data.remote

import com.david.spanisleague.data.local.SoccerLeagueResponse
import com.david.spanisleague.data.local.TeamResponse
import com.david.spanisleague.utils.BASE_URL
import com.david.spanisleague.utils.ID_TEAM
import com.david.spanisleague.utils.NEXT_EVENTS
import com.david.spanisleague.utils.LEAGUE
import com.david.spanisleague.utils.SEARCH_ALL_TEAMS
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * ApiRequest
 *
 * This Interface calls an API with list of SoccerLeagues
 *
 * @author david.mazo
 */
interface ApiRequest {

    @GET(SEARCH_ALL_TEAMS)
    fun getMovieReviewListFromInternet(@Query(LEAGUE) l: String): Call<SoccerLeagueResponse>

    @GET(NEXT_EVENTS)
    fun getTeamListFromInternet(@Query(ID_TEAM) id: String?): Call<TeamResponse>

    companion object {
        val instance: ApiRequest = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiRequest::class.java)
    }
}
