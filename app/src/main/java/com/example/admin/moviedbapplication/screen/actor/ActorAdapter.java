package com.example.admin.moviedbapplication.screen.actor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.admin.moviedbapplication.R;
import com.example.admin.moviedbapplication.data.model.Cast;
import com.example.admin.moviedbapplication.utils.Constants;

import java.util.ArrayList;

/**
 * Created by TamTT on 8/15/2018.
 */

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {

    private ArrayList<Cast> mCasts;
    private Context mContext;
    private OnActorClickListener mOnActorClickListener;

    public void setOnActorClickListener(OnActorClickListener onActorClickListener) {
        mOnActorClickListener = onActorClickListener;
    }

    public ActorAdapter(Context context, ArrayList<Cast> casts) {
        super();
        mContext = context;
        mCasts = casts;
    }

    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_actor, viewGroup, false);
        ActorViewHolder viewHolder = new ActorViewHolder(mContext, v, mOnActorClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ActorViewHolder viewHolder, int i) {
        viewHolder.fillData(mCasts.get(i));
    }

    @Override
    public int getItemCount() {
        return mCasts.size();
    }

    static class ActorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView mImgviewCast;
        private Context mContext;
        private OnActorClickListener mOnActorClickListener;
        private TextView mTextActorName, mTextActorCharacter;
        private Cast mCast;
        public ActorViewHolder(Context context, View itemView, OnActorClickListener onActorClickListener) {
            super(itemView);
            mContext = context;
            mOnActorClickListener = onActorClickListener;
            mImgviewCast = itemView.findViewById(R.id.image_item_actor);
            mTextActorName = itemView.findViewById(R.id.text_item_actor_name);
            mTextActorCharacter = itemView.findViewById(R.id.text_item_actor_character);
            mOnActorClickListener = onActorClickListener;
            itemView.setOnClickListener(this);
        }

        void fillData(Cast cast){
            mCast = cast;
            mTextActorCharacter.setText(cast.getCharacter());
            mTextActorName.setText(cast.getName());
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.mipmap.ic_placeholder)
                    .error(R.mipmap.ic_error_load_image);
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.IMAGE_PATH + cast.getProfilePath())
                    .into(mImgviewCast);
        }

        @Override
        public void onClick(View view) {
            mOnActorClickListener.onActorClick(mCast);
            Toast.makeText(mContext, "Click actor "+ mCast.getName(), Toast.LENGTH_SHORT).show();
        }
    }


}