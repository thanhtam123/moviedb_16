package com.example.admin.moviedbapplication.screen.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Movie;
import com.example.admin.moviedbapplication.screen.detail.DetailActivity;
import com.example.admin.moviedbapplication.screen.genre.GenresActivity;
import com.example.admin.moviedbapplication.utils.Constants;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/7/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MovieRowHolder>{

    private ArrayList<Category> mCategoryMovies = new ArrayList<>();
    private Context mContext;

    public CategoryAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public MovieRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_categoty_movie,viewGroup, false);
        return new MovieRowHolder(mContext,view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRowHolder itemRowHolder, final int i) {
        itemRowHolder.fillData(mCategoryMovies.get(i));
    }

    public void updateData(ArrayList<Category> categoryMovies) {
        if (categoryMovies == null) {
            return;
        }
        mCategoryMovies.addAll(categoryMovies);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return null != mCategoryMovies ? mCategoryMovies.size() : 0;
    }

     static class MovieRowHolder extends RecyclerView.ViewHolder implements
             OnItemMovieClickedListener, View.OnClickListener{
        private TextView mTextCategoryName;
        private RecyclerView mRecyclerMovies;
        private ImageButton mImageMore;
        private Category mCategory;
        private Context mContext;
        MovieRowHolder(Context context, View view) {
            super(view);
            mTextCategoryName = view.findViewById(R.id.text_item_name_category);
            mRecyclerMovies = view.findViewById(R.id.recyclerview_movie);
            mImageMore = view.findViewById(R.id.button_more_movies);
            mContext = context;
        }

        void fillData(Category category) {
            mCategory = category;
            final String categoryName = category.getCategeryName();
            final ArrayList<Movie> movieArrayList = category.getCategoryMovie();
            mTextCategoryName.setText(categoryName);
            MovieAdapter movieItemAdapter = new MovieAdapter(mContext);
            movieItemAdapter.setOnItemClickedListener(this);
            movieItemAdapter.updateData(movieArrayList);
            mRecyclerMovies.setHasFixedSize(true);
            mRecyclerMovies.setLayoutManager(new LinearLayoutManager(mContext,
                    LinearLayoutManager.HORIZONTAL, false));
            mRecyclerMovies.setAdapter(movieItemAdapter);
            mImageMore.setOnClickListener(this);
        }

        @Override
        public void onMovieClick(Movie movie) {
            Log.e("TAG", movie.toString());
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra(Constants.EXTRA_MOVIE, movie);
            mContext.startActivity(intent);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, GenresActivity.class);
            intent.putExtra(Constants.EXTRA_TYPE, mCategory);
            mContext.startActivity(intent);
        }
    }
}
