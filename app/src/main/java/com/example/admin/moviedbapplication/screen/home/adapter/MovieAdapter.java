package com.example.admin.moviedbapplication.screen.home.adapter;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.model.MovieObject;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/3/2018.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {


    private ArrayList<MovieObject> itemsList;
    private Context mContext;

    public MovieAdapter(Context context, ArrayList<MovieObject> itemsList) {
        this.itemsList = itemsList;
        this.mContext = context;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie, null);
        MovieViewHolder mh = new MovieViewHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int i) {
        final MovieObject singleItem = itemsList.get(i);
        holder.text_item_name_movie.setText(singleItem.getmTitle());
        Glide.with(mContext)
                .load(singleItem.getmPosterPath())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .error(R.mipmap.ic_launcher)
                .into(holder.image_item_main_movie);
        holder.rating_item_movie.setRating((float) singleItem.getmVoteAverage());
    }

    @Override
    public int getItemCount() {
        return (null != itemsList ? itemsList.size() : 0);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView text_item_name_movie;
        ImageView image_item_main_movie;
        RatingBar rating_item_movie;
        MovieViewHolder(View view) {
            super(view);
            this.text_item_name_movie = view.findViewById(R.id.text_item_name_movie);
            this. image_item_main_movie = view.findViewById(R.id.image_item_main_movie);
            this.rating_item_movie = view.findViewById(R.id.rating_item_movie);
        }
    }

    public interface OnItemClickedListener {
        void onItemClick(String username);
    }

    private OnItemClickedListener onItemClickedListener;

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }
}
