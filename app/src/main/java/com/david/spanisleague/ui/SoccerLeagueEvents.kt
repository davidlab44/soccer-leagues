package com.david.spanisleague.ui

import com.david.spanisleague.model.SoccerLeague

/**
 * SoccerLeagueEvents
 *
 * This interface is imlemented in the recyclerView items onItemClicked event
 *
 * @author david.mazo
 */
interface SoccerLeagueEvents {
    fun onItemClicked(soccerLeague: SoccerLeague)
}
