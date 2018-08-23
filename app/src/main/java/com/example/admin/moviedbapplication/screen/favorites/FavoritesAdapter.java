package com.example.admin.moviedbapplication.screen.favorites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/13/2018.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MovieFavoriteViewHolder> {

    private Context mContext;
    private OnItemClickListener mListener;
    private List<Movie> mMovies = new ArrayList<>();

    void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    FavoritesAdapter(Context context, List<Movie> arrayList) {
        mContext = context;
        mMovies = arrayList;
    }

    @NonNull
    @Override
    public MovieFavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_favorites, viewGroup, false);
        return new MovieFavoriteViewHolder(mContext, view, mListener);
    }

    @Override
    public void onBindViewHolder(MovieFavoriteViewHolder holder, final int i) {
        holder.fillData(mMovies.get(i));
    }

    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }

    void removeItem(String id) {
        for (Movie movie : mMovies) {
            if (movie.getId().compareToIgnoreCase(id) == 0) {
                mMovies.remove(movie);
                notifyDataSetChanged();
                break;
            }
        }
    }

    static class MovieFavoriteViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener {
        private TextView mTextViewMovieName, mTextViewOverView;
        private ImageView mImageViewPoster;
        private RatingBar mRatingBar;
        private Movie mMovie;
        private CardView mCardView;
        private ImageButton mButtonDelete;
        private Context mContext;
        private OnItemClickListener mListener;

        MovieFavoriteViewHolder(Context context, View view,
                                OnItemClickListener listener) {
            super(view);
            mListener = listener;
            mContext = context;
            mButtonDelete = view.findViewById(R.id.button_delete);
            mTextViewMovieName = view.findViewById(R.id.text_item_movie_title_favorite);
            mImageViewPoster = view.findViewById(R.id.image_item_movie_poster_favorite);
            mRatingBar = view.findViewById(R.id.rating_item_movie_favorite);
            mTextViewOverView = view.findViewById(R.id.text_overview_favorite);
            mCardView = view.findViewById(R.id.card_favorite);
            mCardView.setOnClickListener(this);
        }

        @SuppressLint("CheckResult")
        void fillData(final Movie movie) {
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
            mButtonDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mListener == null) {
                return;
            }
            switch (view.getId()) {
                case R.id.button_delete:
                    mListener.onRemoveClick(mMovie.getId());
                    break;
                case R.id.card_favorite:
                    mListener.onMovieClick(mMovie);
                    break;
            }
        }
    }

    interface OnItemClickListener {
        void onMovieClick(Movie movie);

        void onRemoveClick(String id);
    }
}
