package com.example.admin.moviedbapplication.screen.search;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.screen.home.adapter.OnItemMovieClickedListener;
import com.example.admin.moviedbapplication.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/9/2018.
 */

public class MovieSearchAdapter extends RecyclerView.Adapter<MovieSearchAdapter.MovieViewHolder> {

    private Context mContext;
    private OnItemMovieClickedListener mOnItemClickedListener;
    private List<Movie> mMovies = new ArrayList<>();

    void setOnItemClickedListener(OnItemMovieClickedListener onItemClickedListener) {
        mOnItemClickedListener = onItemClickedListener;
    }

    MovieSearchAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public MovieSearchAdapter.MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_movie_search, viewGroup, false);
        return new MovieSearchAdapter.MovieViewHolder(mContext, view, mOnItemClickedListener);
    }

    @Override
    public void onBindViewHolder(MovieSearchAdapter.MovieViewHolder holder, final int i) {
        holder.fillData(mMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    void updateData(ArrayList<Movie> movies) {
        if (movies == null) {
            return;
        }
        mMovies.clear();
        mMovies.addAll(movies);
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewMovieName, mTextViewGenres;
        private ImageView mImageViewPoster;
        private RatingBar mRatingBar;
        private Movie mMovie;
        private Context mContext;
        private OnItemMovieClickedListener mOnItemMovieClickedListener;

        MovieViewHolder(Context context, View view,
                        OnItemMovieClickedListener onItemMovieClickedListener) {
            super(view);
            mOnItemMovieClickedListener = onItemMovieClickedListener;
            mContext = context;
            mTextViewMovieName = view.findViewById(R.id.text_item_movie_title_search);
            mImageViewPoster = view.findViewById(R.id.image_item_movie_poster);
            mRatingBar = view.findViewById(R.id.rating_item_movie_search);
            mTextViewGenres = view.findViewById(R.id.text_item_movie_genre_search);
            view.setOnClickListener(this);
        }

        @SuppressLint("CheckResult")
        public void fillData(Movie movie) {
            mMovie = movie;
            mTextViewMovieName.setText(movie.getTitle());
            mTextViewGenres.setText(mContext.getResources().getString(R.string.app_name));
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.mipmap.ic_placeholder)
                    .error(R.mipmap.ic_error_load_image);
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.IMAGE_PATH + movie.getPosterPath())
                    .into(mImageViewPoster);
            mRatingBar.setRating((float) (movie.getVoteAverage() * Constants.VOTE));
        }

        @Override
        public void onClick(View view) {
            mOnItemMovieClickedListener.onMovieClick(mMovie);
        }
    }
}
