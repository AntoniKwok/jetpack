package com.antoni.wijaya.jetpackpro.ui.favorite.fragment.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.antoni.wijaya.jetpackpro.R
import com.antoni.wijaya.jetpackpro.data.source.local.entity.TvShowEntity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_shows.view.*

class FavoriteTvShowAdapter(
    private val movies: List<TvShowEntity>,
    private val listener: (TvShowEntity) -> Unit
) :
    PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.TvShowViewHolder>(MoviesDiffCallback) {

    companion object {
        val MoviesDiffCallback = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_shows, parent, false)
        return TvShowViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            listener(movie)
        }

    }


    class TvShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: TvShowEntity) {
            itemView.txt_title.text = movie.title
            itemView.txt_date.text = movie.releasedDate
            itemView.txt_genre.text = movie.genres
            Glide.with(itemView.context)
                .load(movie.imageUrl)
                .into(itemView.img_poster)
            itemView.rating_bar.rating = movie.rating!!.toFloat() / 20

        }

    }
}