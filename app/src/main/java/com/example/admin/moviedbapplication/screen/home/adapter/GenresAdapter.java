package com.example.admin.moviedbapplication.screen.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.moviedbapplication.R;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/3/2018.
 */

public class GenresAdapter extends RecyclerView.Adapter<GenresAdapter.GenreItem> {

    private ArrayList<String> mGenres;
    private Context mContext;

    public GenresAdapter(Context context, ArrayList<String> itemsList) {
        this.mGenres = itemsList;
        this.mContext = context;
    }

    @Override
    public GenreItem onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_genres, null);
        GenreItem mh = new GenreItem(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(GenreItem holder, final int i) {
        holder.tvTitle.setText("More");
    }

    @Override
    public int getItemCount() {
        return (null != mGenres ? mGenres.size() : 0);
    }

    class GenreItem extends RecyclerView.ViewHolder {
        TextView tvTitle;
        GenreItem(View view) {
            super(view);
            this.tvTitle = view.findViewById(R.id.text_name_genres);
        }
    }
}
