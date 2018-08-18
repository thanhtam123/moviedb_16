package com.example.admin.moviedbapplication.data.source.remote.cast;

import android.os.AsyncTask;

import com.example.admin.moviedbapplication.data.model.Cast;
import com.example.admin.moviedbapplication.data.source.Callback;
import com.example.admin.moviedbapplication.utils.Utils;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

/**
 * Created by TamTT on 8/15/2018.
 */

public class CastRemoteAsyntask extends AsyncTask<String, Void, List<Cast>> {

    private Callback<List<Cast>> mCallback;
    private Exception mException;

    public CastRemoteAsyntask(Callback<List<Cast>> callback) {
        mCallback = callback;
    }

    @Override
    protected List<Cast> doInBackground(String... strings) {
        try {
            String json = Utils.getJSONStringFromURL(strings[0]);
            return Utils.parseJsonIntoCasts(json);
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
    protected void onPostExecute(List<Cast> casts) {
        super.onPostExecute(casts);
        if (mCallback == null) {
            return;
        }
        if (mException == null) {
            mCallback.onGetDataSuccess(casts);
        } else {
            mCallback.onGetDataFailure(mException);
        }
    }
}
