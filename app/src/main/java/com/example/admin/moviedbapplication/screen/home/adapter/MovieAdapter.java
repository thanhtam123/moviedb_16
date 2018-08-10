package com.example.admin.moviedbapplication.screen.home.adapter;

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
import com.example.admin.moviedbapplication.utils.Constants;
import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Movie;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/7/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context mContext;
    private OnItemMovieClickedListener mOnItemClickedListener;
    private ArrayList<Movie> mMovieArrayList = new ArrayList<>();

    void setOnItemClickedListener(OnItemMovieClickedListener onItemClickedListener) {
        mOnItemClickedListener = onItemClickedListener;
    }

    MovieAdapter(Context context) {
        this.mContext = context;
    }

    void updateData(ArrayList<Movie> movies) {
        if (movies == null) {
            return;
        }
        mMovieArrayList.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(mContext, view,mOnItemClickedListener);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int i) {
        holder.fillData(mMovieArrayList.get(i));
    }

    @Override
    public int getItemCount() {
        return null != mMovieArrayList ? mMovieArrayList.size() : 0;
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewMovieName;
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
            mTextViewMovieName = view.findViewById(R.id.text_item_name_movie);
            mImageViewPoster = view.findViewById(R.id.image_item_main_movie);
            mRatingBar = view.findViewById(R.id.rating_item_movie_search);
            view.setOnClickListener(this);
        }

        @SuppressLint("CheckResult")
        public void fillData(Movie movie) {
            mMovie = movie;
            mTextViewMovieName.setText(movie.getTitle());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.mipmap.ic_placeholder)
                    .error(R.mipmap.ic_error_load_image);
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.IMAGE_PATH+movie.getPosterPath())
                    .into(mImageViewPoster);
            mRatingBar.setRating((float)( movie.getVoteAverage()*0.5));
        }

        @Override
        public void onClick(View view) {
            mOnItemMovieClickedListener.onMovieClick(mMovie);
        }
    }
}
