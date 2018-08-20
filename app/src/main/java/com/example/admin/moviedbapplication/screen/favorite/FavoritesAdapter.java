package com.example.admin.moviedbapplication.screen.favorite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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
import com.example.admin.moviedbapplication.screen.home.adapter.OnItemMovieClickedListener;
import com.example.admin.moviedbapplication.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TamTT on 8/13/2018.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MovieFavoriteViewHolder>  {

    private Context mContext;
    private OnItemMovieClickedListener mOnItemClickedListener;
    private List<Movie> mMovies = new ArrayList<>();
    private FavoritesContract.Presenter mPresenter;

    void FavoritesAdapter(OnItemMovieClickedListener onItemClickedListener) {
        mOnItemClickedListener = onItemClickedListener;
    }

    public void setOnItemClickedListener(OnItemMovieClickedListener onItemClickedListener) {
        mOnItemClickedListener = onItemClickedListener;
    }

    public FavoritesAdapter(Context context, ArrayList<Movie> arrayList, FavoritesContract.Presenter presenter) {
        mContext = context;
        mMovies = arrayList;
        mPresenter = presenter;
    }

    @NonNull
    @Override
    public MovieFavoriteViewHolder  onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).
                inflate(R.layout.item_favorites, viewGroup, false);
        return new MovieFavoriteViewHolder(mContext, view, mOnItemClickedListener);
    }

    public void updateData(ArrayList<Movie> movies){
        if (movies == null) return;
        mMovies.addAll(mMovies.size(),movies);
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(MovieFavoriteViewHolder holder, final int i) {
        holder.fillData(mMovies.get(i));
    }

    public void deleleMovie(Movie movie){
        mPresenter.removeMovie(movie.getId());
        mMovies.remove(movie);
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mMovies == null ? 0 : mMovies.size();
    }


    class MovieFavoriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextViewMovieName, mTextViewOverView;
        private ImageView mImageViewPoster;
        private RatingBar mRatingBar;
        private Movie mMovie;
        private ImageButton mButtonDelete;
        private Context mContext;
        private OnItemMovieClickedListener mOnItemMovieClickedListener;

        MovieFavoriteViewHolder(Context context, View view,
                             OnItemMovieClickedListener onItemMovieClickedListener) {
            super(view);
            mOnItemMovieClickedListener = onItemMovieClickedListener;
            mContext = context;
            mButtonDelete = view.findViewById(R.id.button_delete);
            mTextViewMovieName = view.findViewById(R.id.text_item_movie_title_favorite);
            mImageViewPoster = view.findViewById(R.id.image_item_movie_poster_favorite);
            mRatingBar = view.findViewById(R.id.rating_item_movie_favorite);
            mTextViewOverView = view.findViewById(R.id.text_overview_favorite);
            view.setOnClickListener(this);
        }

        @SuppressLint("CheckResult")
        public void fillData(final Movie movie) {
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
            mButtonDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteMovieFromFavorite(movie);
                }
            });
        }

        private void deleteMovieFromFavorite(final Movie movie) {
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext, R.style.AlertDialogTheme);
            alertDialog.setTitle(mContext.getString(R.string.msg_confirm_delete));
            alertDialog.setMessage(mContext.getResources().getString(R.string.msg_delete_favorite));
            alertDialog.setIcon(R.drawable.ic_confirm);
            alertDialog.setPositiveButton(mContext.getResources().getText(R.string.delete),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            deleleMovie(movie);
                        }
                    });
            alertDialog.setNegativeButton(mContext.getResources().getString(R.string.cancel),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,
                                            int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }

        @Override
        public void onClick(View view) {
            mOnItemMovieClickedListener.onMovieClick(mMovie);
        }
    }
}