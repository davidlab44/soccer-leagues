package com.david.spanisleague.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.david.spanisleague.R
import com.david.spanisleague.model.MovieReview
import kotlinx.android.synthetic.main.list_item.view.*

/**
 * MovieReviewListAdapter
 *
 * Provides access to the data items, makes a View for each item in the data set
 *
 * @author david.mazo
 */
class MovieReviewListAdapter(private val movieReviewEvents: MovieReviewEvents) :
        RecyclerView.Adapter<MovieReviewListAdapter.ViewHolder>() {

    private var listMovieReview: List<MovieReview> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return listMovieReview.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(listMovieReview[position], movieReviewEvents)
    }

    fun addAll(listMovieReview: List<MovieReview>) {
        this.listMovieReview = listMovieReview
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(movieReview: MovieReview, listener: MovieReviewEvents) {
            itemView.original_title.text = movieReview.strTeam
            //rating_bar.rating = movieReview.voteAverage.toFloat() / getFactorMovieReviewRating()
            Glide.with(itemView)
                    .load(movieReview.strTeamBadge)
                    .centerCrop()
                    .fitCenter()
                    .override(1000, 1000)
                    .into(itemView.movie_image)
            view.setOnClickListener { listener.onItemClicked(movieReview) }
        }
    }
}
