package com.david.spanisleague.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.david.spanisleague.data.local.SoccerLeague
import com.david.spanisleague.data.local.SoccerLeagueResponse
import com.david.spanisleague.repository.SoccerLeagueRepository


class MyViewModel : ViewModel() {

        val liveData = MutableLiveData <SoccerLeagueResponse>()
        val myRepository = SoccerLeagueRepository()

        fun callApi(league:String) {
            myRepository.requestMovieReviewList(league,liveData)
        }
    }
