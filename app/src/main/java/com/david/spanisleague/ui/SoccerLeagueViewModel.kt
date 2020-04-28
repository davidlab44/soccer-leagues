package com.david.spanisleague.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.spanisleague.data.local.SoccerLeagueResponse
import com.david.spanisleague.repository.SoccerLeagueRepository

/**
 * SoccerLeagueViewModel
 *
 * SoccerLeagueViewModel to persist SoccerLeague data
 *
 * @author david.mazo
 */
class SoccerLeagueViewModel : ViewModel() {
    val soccerLeagueLiveData = MutableLiveData <SoccerLeagueResponse>()
    private val soccerLeagueRepository = SoccerLeagueRepository()

    fun callApi(league:String) {
        soccerLeagueRepository.requestMovieReviewList(league,soccerLeagueLiveData)
    }
}
