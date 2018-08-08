package com.example.admin.moviedbapplication.screen.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Genre;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/7/2018.
 */

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenreItem> {

    private ArrayList<Genre> mGenres = new ArrayList<>();
    private Context mContext;
    private OnGenreItemClickListener mOnGenreItemClickListener;

    public void setOnItemClickedListener(OnGenreItemClickListener onGenreItemClickListener) {
        mOnGenreItemClickListener = onGenreItemClickListener;
    }

    public GenresAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public GenreItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_genres, viewGroup, false);
        return new GenreItem(view, mOnGenreItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreItem holder, final int i) {
        holder.fillData(mGenres.get(i));
    }

    public void updateData(ArrayList<Genre> users) {
        if (users == null) {
            return;
        }
        mGenres.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return null != mGenres ? mGenres.size() : 0;
    }

    static class GenreItem extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTextViewGenreName;
        private Genre mGenre;
        private OnGenreItemClickListener mOnGenreItemClickListener;

        GenreItem(View view, OnGenreItemClickListener onGenreItemClickListener) {
            super(view);
            mOnGenreItemClickListener = onGenreItemClickListener;
            mTextViewGenreName = view.findViewById(R.id.text_name_genres);
            view.setOnClickListener(this);
        }

        void fillData(Genre genre) {
            mGenre = genre;
            mTextViewGenreName.setText(genre.getGenreName());
        }

        @Override
        public void onClick(View view) {
            mOnGenreItemClickListener.onGenreClick(mGenre);
        }
    }
}
