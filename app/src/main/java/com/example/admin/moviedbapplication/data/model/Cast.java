package com.example.admin.moviedbapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by TamTT on 8/10/2018.
 */

public class Cast implements Parcelable{
    private String mCastId;
    private String mCharacter;
    private String mCreditId;
    private String mGender;
    private String mId;
    private String mName;
    private String mOrder;
    private String mProfilePath;

    public Cast() {
    }

    protected Cast(Parcel in) {
        mCastId = in.readString();
        mCharacter = in.readString();
        mCreditId = in.readString();
        mGender = in.readString();
        mId = in.readString();
        mName = in.readString();
        mOrder = in.readString();
        mProfilePath = in.readString();
    }

    public static final Creator<Cast> CREATOR = new Creator<Cast>() {
        @Override
        public Cast createFromParcel(Parcel in) {
            return new Cast(in);
        }

        @Override
        public Cast[] newArray(int size) {
            return new Cast[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mCastId);
        parcel.writeString(mCharacter);
        parcel.writeString(mCreditId);
        parcel.writeString(mGender);
        parcel.writeString(mId);
        parcel.writeString(mName);
        parcel.writeString(mOrder);
        parcel.writeString(mProfilePath);
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
