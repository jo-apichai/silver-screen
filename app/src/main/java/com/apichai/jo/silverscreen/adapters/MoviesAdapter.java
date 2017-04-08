package com.apichai.jo.silverscreen.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.apichai.jo.silverscreen.R;
import com.apichai.jo.silverscreen.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private Context mContext;
    private List<Movie> mMovies;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movies_poster)
        public ImageView moviePoster;

        @BindView(R.id.movie_title)
        public TextView movieTitle;

        public MovieViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public MoviesAdapter(Context context, List<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);

        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.movieTitle.setText(movie.getTitle());
        Picasso.with(mContext).load(movie.getThumbnailPath()).into(holder.moviePoster);
    }
}
