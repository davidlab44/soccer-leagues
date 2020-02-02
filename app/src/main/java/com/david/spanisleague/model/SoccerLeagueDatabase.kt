package com.david.spanisleague.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * SoccerLeagueDatabase
 *
 * We can use it to create other classes
 * It is abstract because it is implemented by Room
 *
 * @author david.mazo
 */

@Database(entities = [SoccerLeague::class, TeamReview::class], version = 11, exportSchema = false)
abstract class SoccerLeagueDatabase : RoomDatabase() {

    abstract fun getMovieDAO(): MovieDao
    abstract fun getTeamEventDAO(): TeamDao

    companion object {
        fun getMovieDatabase(context: Context) = Room.databaseBuilder(context.applicationContext,
                SoccerLeagueDatabase::class.java, "SoccerLeagueDatabase")
                .allowMainThreadQueries()
                .build()
    }
}
