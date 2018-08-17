package com.example.admin.moviedbapplication.screen;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by TamTT on 8/16/2018.
 */

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private int currentPage = 0;
    private int previousTotalItemCount = 0;
    private boolean loading = true;

    private LinearLayoutManager mLinearLayoutManager;

    protected EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.mLinearLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int visibleItemCount = view.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();

        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = 0;
            this.previousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.loading = true;
            }
        }
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        int visibleThreshold = 7;
        if (!loading && (totalItemCount - visibleItemCount) <=
                (firstVisibleItem + visibleThreshold)) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount);
            loading = true;
        }
    }

    public abstract void onLoadMore(int page, int totalItemsCount);
}
