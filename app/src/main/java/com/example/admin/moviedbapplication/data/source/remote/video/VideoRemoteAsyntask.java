package com.example.admin.moviedbapplication.data.source.remote.video;

import android.os.AsyncTask;

import com.example.admin.moviedbapplication.data.model.Video;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.utils.Utils;

import org.json.JSONException;

import java.io.IOException;

/**
 * Created by TamTT on 8/10/2018.
 */

public class VideoRemoteAsyntask extends AsyncTask<String, Void, Video> {

    private Callback<Video> mCallback;
    private Exception mException;

    public VideoRemoteAsyntask(Callback<Video> callback) {
        mCallback = callback;
    }

    @Override
    protected Video doInBackground(String... strings) {
        try {
            String json = Utils.getJSONStringFromURL(strings[0]);
            return Utils.parseJsonIntoVideo(json);
        } catch (IOException e) {
            e.printStackTrace();
            mException = e;
        } catch (JSONException e) {
            e.printStackTrace();
            mException = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Video video) {
        super.onPostExecute(video);
        if (mCallback == null) {
            return;
        }
        if (mException == null) {
            mCallback.onGetDataSuccess(video);
        } else {
            mCallback.onGetDataFailure(mException);
        }
    }
}
