package com.example.admin.moviedbapplication.model;

import java.util.Collection;
import java.util.List;

/**
 * Created by TamTT on 8/3/2018.
 */

public class MovieObject {
    private Integer mVoteCount;
    private Integer mId;
    private Boolean mVideo;
    private Double mVoteAverage;
    private String mTitle;
    private Double mPopularity;
    private String mPosterPath;
    private String mOriginalLanguage;
    private String mOriginalTitle;
    private List<Integer> mGenreIds = null;
    private String mBackdropPath;
    private Boolean mAdult;
    private String mOverview;
    private String mReleaseDate;

    public MovieObject(String mOriginalLanguage, String mImdbId, boolean mVideo, String mTitle,
                       String backdropPath, List<Integer> mGenres, double mPopularity, int mId,
                       int mVoteCount, String mOverview, String mOriginalTitle,
                       String mPosterPath, String mReleaseDate, double mVoteAverage, Collection mCollection,
                       String mTagline, boolean mAdult, String mHomepage, String mStatus) {
        this.mOriginalLanguage = mOriginalLanguage;
        this.mVideo = mVideo;
        this.mTitle = mTitle;
        this.mBackdropPath = backdropPath;
        this.mGenreIds = mGenres;
        this.mPopularity = mPopularity;
        this.mId = mId;
        this.mVoteCount = mVoteCount;
        this.mOverview = mOverview;
        this.mOriginalTitle = mOriginalTitle;
        this.mPosterPath = mPosterPath;
        this.mReleaseDate = mReleaseDate;
        this.mVoteAverage = mVoteAverage;
        this.mAdult = mAdult;
    }

    public String getmOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setmOriginalLanguage(String mOriginalLanguage) {
        this.mOriginalLanguage = mOriginalLanguage;
    }

    public boolean ismVideo() {
        return mVideo;
    }

    public void setmVideo(boolean mVideo) {
        this.mVideo = mVideo;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.mBackdropPath = backdropPath;
    }

    public List<Integer> getmGenres() {
        return mGenreIds;
    }

    public void setmGenres(List<Integer> mGenres) {
        this.mGenreIds = mGenres;
    }

    public double getmPopularity() {
        return mPopularity;
    }

    public void setmPopularity(double mPopularity) {
        this.mPopularity = mPopularity;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public int getmVoteCount() {
        return mVoteCount;
    }

    public void setmVoteCount(int mVoteCount) {
        this.mVoteCount = mVoteCount;
    }

    public String getmOverview() {
        return mOverview;
    }

    public void setmOverview(String mOverview) {
        this.mOverview = mOverview;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String getmPosterPath() {
        return mPosterPath;
    }

    public void setmPosterPath(String mPosterPath) {
        this.mPosterPath = mPosterPath;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public double getmVoteAverage() {
        return mVoteAverage;
    }

    public void setmVoteAverage(double mVoteAverage) {
        this.mVoteAverage = mVoteAverage;
    }

    public boolean ismAdult() {
        return mAdult;
    }

    public void setmAdult(boolean mAdult) {
        this.mAdult = mAdult;
    }
}
