package com.david.spanisleague.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.david.spanisleague.R
import com.david.spanisleague.model.SoccerLeague
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * SoccerLeagueListAdapter
 *
 * Provides access to the SoccerLeague data items, makes a View for each SoccerLeague item
 *
 * @author david.mazo
 */
class SoccerLeagueListAdapter(private val soccerLeagueEvents: SoccerLeagueEvents) :
        RecyclerView.Adapter<SoccerLeagueListAdapter.ViewHolder>() {

    private var listSoccerLeague: List<SoccerLeague> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listSoccerLeague.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listSoccerLeague[position], soccerLeagueEvents)
    }

    fun addAll(listSoccerLeague: List<SoccerLeague>) {
        this.listSoccerLeague = listSoccerLeague
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(soccerLeague: SoccerLeague, listener: SoccerLeagueEvents) {
            itemView.original_title.text = soccerLeague.strTeam
            //rating_bar.rating = soccerLeague.voteAverage.toFloat() / getFactorMovieReviewRating()
            Glide.with(itemView)
                    .load(soccerLeague.strTeamBadge)
                    .centerCrop()
                    .fitCenter()
                    .override(1000, 1000)
                    .into(itemView.movie_image)
            view.setOnClickListener { listener.onItemClicked(soccerLeague) }
        }
    }
}
