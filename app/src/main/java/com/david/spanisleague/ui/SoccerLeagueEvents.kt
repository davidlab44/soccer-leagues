package com.david.spanisleague.ui

import com.david.spanisleague.data.local.SoccerLeague

/**
 * SoccerLeagueEvents
 *
 * This interface is implemented in activities to manage recyclerView items onItemClick event
 *
 * @author david.mazo
 */
interface SoccerLeagueEvents {
    fun onItemClicked(soccerLeague: SoccerLeague)
}
