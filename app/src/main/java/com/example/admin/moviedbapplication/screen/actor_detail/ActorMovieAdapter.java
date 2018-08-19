package com.example.admin.moviedbapplication.screen.actor_detail;

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
 * Created by TamTT on 8/15/2018.
 */

public class ActorMovieAdapter extends RecyclerView.Adapter<ActorMovieAdapter.MovieAdapter> {

    private Context mContext;
    private OnItemMovieClickedListener mOnItemClickedListener;
    private List<Movie> mMovies = new ArrayList<>();

    public void setOnItemClickedListener(OnItemMovieClickedListener onItemClickedListener) {
        mOnItemClickedListener = onItemClickedListener;
    }

    public ActorMovieAdapter(Context context, ArrayList<Movie> arrayList) {
        mContext = context;
        mMovies = arrayList;
    }

    @NonNull
    @Override
    public MovieAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_genre, viewGroup, false);

        return new MovieAdapter(mContext, v, mOnItemClickedListener);
    }

    @Override
    public void onBindViewHolder(MovieAdapter holder, final int i) {
        holder.fillData(mMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    static class MovieAdapter extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewMovieName, mTextViewOverView;
        private ImageView mImageViewPoster;
        private RatingBar mRatingBar;
        private Movie mMovie;
        private Context mContext;
        private OnItemMovieClickedListener mOnItemMovieClickedListener;

        MovieAdapter(Context context, View view,
                             OnItemMovieClickedListener onItemMovieClickedListener) {
            super(view);
            mOnItemMovieClickedListener = onItemMovieClickedListener;
            mContext = context;
            mTextViewMovieName = view.findViewById(R.id.text_item_movie_title_genre);
            mImageViewPoster = view.findViewById(R.id.image_item_movie_poster_genre);
            mRatingBar = view.findViewById(R.id.rating_item_movie_genre);
            mTextViewOverView = view.findViewById(R.id.text_overview_genre);
            view.setOnClickListener(this);
        }

        @SuppressLint("CheckResult")
        public void fillData(Movie movie) {
            mMovie = movie;
            mTextViewMovieName.setText(movie.getTitle());
            mTextViewOverView.setText(movie.getOverview());
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