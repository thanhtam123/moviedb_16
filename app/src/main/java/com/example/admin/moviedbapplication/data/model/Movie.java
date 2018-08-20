package com.example.admin.moviedbapplication.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by TamTT on 8/6/2018.
 */

public class Movie implements Parcelable{
    private int mVoteCount;
    private String mId;
    private boolean mVideo;
    private double mVoteAverage;
    private String mTitle;
    private double mPopularity;
    private String mPosterPath;
    private String mOriginalLanguage;
    private String mOriginalTitle;
    private int[] mGenreIds;
    private String mBackdropPath;
    private boolean mAdult;
    private String mOverview;
    private String mReleaseDate;

    private Movie(Builder builder) {
        mVoteCount = builder.mVoteCount;
        mId = builder.mId;
        mVideo = builder.mVideo;
        mVoteAverage = builder.mVoteAverage;
        mTitle = builder.mTitle;
        mPopularity = builder.mPopularity;
        mPosterPath = builder.mPosterPath;
        mOriginalLanguage = builder.mOriginalLanguage;
        mOriginalTitle = builder.mOriginalTitle;
        mGenreIds = builder.mGenreIds;
        mBackdropPath = builder.mBackdropPath;
        mAdult = builder.mAdult;
        mOverview = builder.mOverview;
        mReleaseDate = builder.mRelaseDate;
    }

    public Movie(JSONObject object) throws JSONException {
        mVoteCount = object.getInt(Movie.JsonKey.VOTE_COUNT);
        mId = object.getString(Movie.JsonKey.ID);
        mVideo = object.getBoolean(Movie.JsonKey.VIDEO);
        mAdult = object.getBoolean(Movie.JsonKey.ADULT);
        mVoteAverage = object.getDouble(Movie.JsonKey.VOTE_AVERAGE);
        mTitle = object.getString(Movie.JsonKey.TITLE);
        mPopularity = object.getDouble(Movie.JsonKey.POPULARITY);
        mPosterPath = object.getString(Movie.JsonKey.POSTER_PATH);
        mOriginalLanguage = object.getString(Movie.JsonKey.ORIGINAL_LANGUAGE);
        mOriginalTitle = object.getString(Movie.JsonKey.ORIGINAL_TITLE);
        mBackdropPath = object.getString(Movie.JsonKey.BACKDROP_PATH);
        mOverview = object.getString(Movie.JsonKey.OVERVIEW);
        mReleaseDate = object.getString(Movie.JsonKey.RELEASE_DATE);
        JSONArray genresArray = object.optJSONArray(Movie.JsonKey.GENRE_IDS);
        if(genresArray == null){
            JSONArray genres = object.optJSONArray(JsonKey.GENRES);
            mGenreIds = new int[genres.length()];
            for (int j = 0; j < genres.length(); j++) {
                mGenreIds[j] = genres.getJSONObject(j).getInt(JsonKey.ID_GENRE);
            }
        }else {
            mGenreIds = new int[genresArray.length()];
            for (int j = 0; j < genresArray.length(); j++) {
                mGenreIds[j] = genresArray.optInt(j);
            }
        }
    }

    protected Movie(Parcel in) {
        mVoteCount = in.readInt();
        mId = in.readString();
        mVideo = in.readByte() != 0;
        mVoteAverage = in.readDouble();
        mTitle = in.readString();
        mPopularity = in.readDouble();
        mPosterPath = in.readString();
        mOriginalLanguage = in.readString();
        mOriginalTitle = in.readString();
        mGenreIds = in.createIntArray();
        mBackdropPath = in.readString();
        mAdult = in.readByte() != 0;
        mOverview = in.readString();
        mReleaseDate = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getVoteCount() {
        return mVoteCount;
    }

    public void setVoteCount(int voteCount) {
        mVoteCount = voteCount;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public boolean isVideo() {
        return mVideo;
    }

    public void setVideo(boolean video) {
        mVideo = video;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        mVoteAverage = voteAverage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(double popularity) {
        mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        mPosterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public int[] getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(int[] genreIds) {
        mGenreIds = genreIds;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        mBackdropPath = backdropPath;
    }

    public boolean isAdult() {
        return mAdult;
    }

    public void setAdult(boolean adult) {
        mAdult = adult;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "mVoteCount=" + mVoteCount +
                ", mTitle='" + mTitle + '\'' +
                ", mPopularity=" + mPopularity +
                ", mPosterPath='" + mPosterPath + '\'' +
                ", mGenreIds=" + Arrays.toString(mGenreIds) +
                ", mBackdropPath='" + mBackdropPath + '\'' +
                ", mOverview='" + mOverview + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mVoteCount);
        parcel.writeString(mId);
        parcel.writeByte((byte) (mVideo ? 1 : 0));
        parcel.writeDouble(mVoteAverage);
        parcel.writeString(mTitle);
        parcel.writeDouble(mPopularity);
        parcel.writeString(mPosterPath);
        parcel.writeString(mOriginalLanguage);
        parcel.writeString(mOriginalTitle);
        parcel.writeIntArray(mGenreIds);
        parcel.writeString(mBackdropPath);
        parcel.writeByte((byte) (mAdult ? 1 : 0));
        parcel.writeString(mOverview);
        parcel.writeString(mReleaseDate);
    }

    public static class Builder {
        private int mVoteCount;
        private String mId;
        private boolean mVideo;
        private double mVoteAverage;
        private String mTitle;
        private double mPopularity;
        private String mPosterPath;
        private String mOriginalLanguage;
        private String mOriginalTitle;
        private int[] mGenreIds;
        private String mBackdropPath;
        private boolean mAdult;
        private String mOverview;
        private String mRelaseDate;

        public Builder setVoteCount(int voteCount) {
            mVoteCount = voteCount;
            return this;
        }

        public Builder setId(String id) {
            mId = id;
            return this;
        }

        public Builder setVideo(boolean video) {
            mVideo = video;
            return this;
        }

        public Builder setVoteAverage(double voteAverage) {
            mVoteAverage = voteAverage;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setPopularity(double popularity) {
            mPopularity = popularity;
            return this;
        }

        public Builder setPosterPath(String posterPath) {
            mPosterPath = posterPath;
            return this;
        }

        public Builder setOriginalLanguage(String originalLanguage) {
            mOriginalLanguage = originalLanguage;
            return this;
        }

        public Builder setOriginalTitle(String originalTitle) {
            mOriginalTitle = originalTitle;
            return this;
        }

        public Builder setGenreIds(int[] genreIds) {
            mGenreIds = genreIds;
            return this;
        }

        public Builder setBackdropPath(String backdropPath) {
            mBackdropPath = backdropPath;
            return this;
        }

        public Builder setAdult(boolean adult) {
            mAdult = adult;
            return this;
        }

        public Builder setOverview(String overview) {
            mOverview = overview;
            return this;
        }

        public Builder setRelaseDate(String relaseDate) {
            mRelaseDate = relaseDate;
            return this;
        }

        public Movie create() {
            return new Movie(this);
        }
    }

    public interface JsonKey {
        String RESULT = "results";
        String VOTE_COUNT = "vote_count";
        String ID = "id";
        String VIDEO = "video";
        String VOTE_AVERAGE = "vote_average";
        String TITLE = "title";
        String POPULARITY = "popularity";
        String POSTER_PATH = "poster_path";
        String ORIGINAL_LANGUAGE = "original_language";
        String ORIGINAL_TITLE = "original_title";
        String GENRE_IDS = "genre_ids";
        String BACKDROP_PATH = "backdrop_path";
        String ADULT = "adult";
        String OVERVIEW = "overview";
        String RELEASE_DATE = "release_date";
        String GENRES = "genres";
        String ID_GENRE = "id";
        String NAME_GENRE = "name";
        String PERSON = "person";
        String KNOWN_FOR = "known_for";
    }
}
