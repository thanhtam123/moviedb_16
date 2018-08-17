package com.example.admin.moviedbapplication.data.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by TamTT on 8/10/2018.
 */

public class Cast {
    private String mCastId;
    private String mCharacter;
    private String mCreditId;
    private String mGender;
    private String mId;
    private String mName;
    private String mOrder;
    private String mProfilePath;

    public Cast(JSONObject jsonObject) throws JSONException{
        mCastId = jsonObject.getString(Cast.ActorJsonKey.CAST_ID);
        mCharacter = jsonObject.getString(Cast.ActorJsonKey.CHARACTER);
        mCreditId = jsonObject.getString(Cast.ActorJsonKey.CREDIT_ID);
        mGender = jsonObject.getString(Cast.ActorJsonKey.GENDER);
        mId = jsonObject.getString(Cast.ActorJsonKey.ID);
        mName = jsonObject.getString(Cast.ActorJsonKey.NAME);
        mProfilePath = jsonObject.getString(Cast.ActorJsonKey.PROFILE_PATH);
    }

    public String getCastId() {
        return mCastId;
    }

    public void setCastId(String castId) {
        mCastId = castId;
    }

    public String getCharacter() {
        return mCharacter;
    }

    public void setCharacter(String character) {
        mCharacter = character;
    }

    public String getCreditId() {
        return mCreditId;
    }

    public void setCreditId(String creditId) {
        mCreditId = creditId;
    }

    public String getGender() {
        return mGender;
    }

    public void setGender(String gender) {
        mGender = gender;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getOrder() {
        return mOrder;
    }

    public void setOrder(String order) {
        mOrder = order;
    }

    public String getProfilePath() {
        return mProfilePath;
    }

    public void setProfilePath(String profilePath) {
        mProfilePath = profilePath;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "mCastId='" + mCastId + '\'' +
                ", mCharacter='" + mCharacter + '\'' +
                ", mCreditId='" + mCreditId + '\'' +
                ", mGender='" + mGender + '\'' +
                ", mId='" + mId + '\'' +
                ", mName='" + mName + '\'' +
                ", mOrder='" + mOrder + '\'' +
                ", mProfilePath='" + mProfilePath + '\'' +
                '}';
    }

    public interface ActorJsonKey{
        String CAST_ID = "cast_id";
        String CHARACTER = "character";
        String CREDIT_ID = "credit_id";
        String GENDER = "gender";
        String ID = "id";
        String NAME = "name";
        String ORDER = "order";
        String PROFILE_PATH = "profile_path";
        String CAST = "cast";
    }
}
