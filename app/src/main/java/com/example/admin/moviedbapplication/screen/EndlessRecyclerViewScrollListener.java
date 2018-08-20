package com.example.admin.moviedbapplication.screen;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by TamTT on 8/16/2018.
 */

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private int mCurrentPage = 0;
    private int mPreviousTotalItemCount = 0;
    private boolean mIsLoading = true;

    private LinearLayoutManager mLinearLayoutManager;

    protected EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.mLinearLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int visibleItemCount = view.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();

        if (totalItemCount < mPreviousTotalItemCount) {
            this.mCurrentPage = 0;
            this.mPreviousTotalItemCount = totalItemCount;
            if (this.mIsLoading = totalItemCount == 0) {
                this.mIsLoading = true;
            }
        }
        if (mIsLoading && (totalItemCount > mPreviousTotalItemCount)) {
            mIsLoading = false;
            mPreviousTotalItemCount = totalItemCount;
        }

        int visibleThreshold = 7;
        if (!mIsLoading && (totalItemCount - visibleItemCount) <=
                (firstVisibleItem + visibleThreshold)) {
            mCurrentPage++;
            onLoadMore(mCurrentPage, totalItemCount);
            mIsLoading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemsCount);
}
