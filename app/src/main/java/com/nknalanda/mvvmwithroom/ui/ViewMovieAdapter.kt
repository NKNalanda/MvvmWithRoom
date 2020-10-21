package com.nknalanda.mvvmwithroom.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nknalanda.mvvmwithroom.R
import com.nknalanda.mvvmwithroom.data.model.MovieEntity
import com.nknalanda.mvvmwithroom.databinding.AdapterViewMovieBinding

class ViewMovieAdapter(
    private var listener: IOnItemClickListener
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var movieList: List<MovieEntity> = ArrayList()
    private var binding: AdapterViewMovieBinding? = null

    interface IOnItemClickListener {
        fun onClickDelete(item: MovieEntity)
        fun onClickEdit(item: MovieEntity)
    }

    fun updateMovieList(movies: List<MovieEntity>) {
        this.movieList = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = AdapterViewMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )

        return ViewMovieViewHolder(binding!!.root);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        var currentMovie = movieList[position]
        val viewHolder: ViewMovieViewHolder = holder as ViewMovieViewHolder
        viewHolder.movieName.text = currentMovie.movieName
        viewHolder.movieYear.text = currentMovie.movieYear
        viewHolder.deleteMovie.setOnClickListener {
            listener.onClickDelete(currentMovie)
        }
        viewHolder.editMovie.setOnClickListener{
            listener.onClickEdit(currentMovie)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewMovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val movieName: TextView = itemView.findViewById(R.id.tv_movie_name)
        val movieYear: TextView = itemView.findViewById(R.id.tv_movie_year)
        val deleteMovie: ImageView = itemView.findViewById(R.id.delete_movie)
        val editMovie: ImageView = itemView.findViewById(R.id.edit_movie)
    }
}