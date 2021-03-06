package com.example.admin.moviedbapplication.screen.genre;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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
 * Created by TamTT on 8/11/2018.
 */

public class MovieGenreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private OnItemMovieClickedListener mOnItemClickedListener;
    private List<Movie> mMovies = new ArrayList<>();
    private final int VIEW_ITEM = 1;
    private final int VIEW_PROG = 0;

    public void setOnItemClickedListener(OnItemMovieClickedListener onItemClickedListener) {
        mOnItemClickedListener = onItemClickedListener;
    }

    public MovieGenreAdapter(Context context, ArrayList<Movie> movies) {
        mContext = context;
        mMovies = movies;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder vh;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (i == VIEW_ITEM) {
            View v = inflater.inflate(R.layout.item_genre, viewGroup, false);
            vh = new MovieGenreViewHolder(mContext, v, mOnItemClickedListener);
        } else {
            View v = inflater.inflate(R.layout.item_loading, viewGroup, false);
            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        return mMovies.get(position) == null ? VIEW_PROG : VIEW_ITEM;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int i) {
        if (getItemViewType(i) == VIEW_ITEM) {
            ((MovieGenreViewHolder) holder).fillData(mMovies.get(i));
        } else {
            ((ProgressViewHolder) holder).mProgressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    public void addLoadingIndicator() {
        mMovies.add(mMovies.size(), null);
        notifyItemInserted(mMovies.size() - 1);
    }

    public void removeLoadingIndicator() {
        for (int i = 0; i<mMovies.size(); i++){
            if(mMovies.get(i) == null){
                mMovies.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i,mMovies.size());
            }
        }
    }

    static class MovieGenreViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private TextView mTextViewMovieName, mTextViewOverView;
        private ImageView mImageViewPoster;
        private RatingBar mRatingBar;
        private Movie mMovie;
        private Context mContext;
        private OnItemMovieClickedListener mOnItemMovieClickedListener;

        MovieGenreViewHolder(Context context, View view,
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

    public static class ProgressViewHolder extends RecyclerView.ViewHolder {
        ProgressBar mProgressBar;

        ProgressViewHolder(View v) {
            super(v);
            mProgressBar = v.findViewById(R.id.progressBar);
        }
    }
}
