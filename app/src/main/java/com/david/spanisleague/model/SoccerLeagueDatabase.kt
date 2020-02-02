package com.david.spanisleague.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * SoccerLeagueDatabase
 *
 * We will use this class to create other classes
 * This class is abstract because is implemented by Room
 *
 * @author david.mazo
 */

@Database(entities = [SoccerLeague::class, TeamReview::class], version = 13, exportSchema = false)
abstract class SoccerLeagueDatabase : RoomDatabase() {

    abstract fun getMovieDAO(): SoccerLeagueDao
    abstract fun getTeamEventDAO(): TeamDao

    companion object {
        fun getMovieDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
                SoccerLeagueDatabase::class.java, "SoccerLeagueDatabase")
                .allowMainThreadQueries()
                .build()
    }
}
