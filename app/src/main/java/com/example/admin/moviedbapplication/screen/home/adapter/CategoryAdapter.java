package com.example.admin.moviedbapplication.screen.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Category;
import com.example.admin.moviedbapplication.data.model.Movie;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/7/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MovieRowHolder> {

    private ArrayList<Category> mCategories = new ArrayList<>();
    private Context mContext;
    private OnItemClickListener mListener;

    public CategoryAdapter(Context context) {
        this.mContext = context;
    }

    public void setListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public MovieRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_categoty_movie, viewGroup, false);
        return new MovieRowHolder(mContext, view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieRowHolder itemRowHolder, final int i) {
        itemRowHolder.fillData(mCategories.get(i));
    }

    public void updateData(ArrayList<Category> categories) {
        if (categories == null) {
            return;
        }
        mCategories.addAll(categories);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return null != mCategories ? mCategories.size() : 0;
    }

    static class MovieRowHolder extends RecyclerView.ViewHolder implements
            OnItemMovieClickedListener, View.OnClickListener {
        private TextView mTextCategoryName;
        private RecyclerView mRecyclerMovies;
        private ImageButton mImageMore;
        private Category mCategory;
        private Context mContext;
        private OnItemClickListener mListener;

        MovieRowHolder(Context context, View view, OnItemClickListener listener) {
            super(view);
            mContext = context;
            mListener = listener;
            mTextCategoryName = view.findViewById(R.id.text_item_name_category);
            mRecyclerMovies = view.findViewById(R.id.recyclerview_movie);
            mImageMore = view.findViewById(R.id.button_more_movies);
        }

        private void fillData(Category category) {
            mCategory = category;
            String categoryName = category.getCategeryName();
            mTextCategoryName.setText(categoryName);
            initMovieRecyclerView(category);
        }

        private void initMovieRecyclerView(Category category) {
            ArrayList<Movie> movies = category.getCategoryMovie();
            MovieAdapter movieItemAdapter = new MovieAdapter(mContext);
            movieItemAdapter.setOnItemClickedListener(mListener);
            movieItemAdapter.updateData(movies);

            mRecyclerMovies.setHasFixedSize(true);
            mRecyclerMovies.setLayoutManager(new LinearLayoutManager(mContext,
                    LinearLayoutManager.HORIZONTAL, false));
            mRecyclerMovies.setAdapter(movieItemAdapter);
            mImageMore.setOnClickListener(this);
        }

        @Override
        public void onMovieClick(Movie movie) {
            if (mListener == null) {
                return;
            }
            mListener.onMovieClick(movie);
        }

        @Override
        public void onClick(View view) {
            if (mListener == null) {
                return;
            }
            mListener.onCategoryClick(mCategory);
        }
    }

    public interface OnItemClickListener {
        void onMovieClick(Movie movie);

        void onCategoryClick(Category category);
    }
}
