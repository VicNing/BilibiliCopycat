package com.vicning.android.bibilicopycat.ui.widgets;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Neil on 2016/8/15.
 */
public abstract class LoadMoreScrollListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager mLayoutManager;
    private boolean isLoading;
    private int visibleThreshold = 3;
    private int previousTotalItemCount = 0;
    private int currentPage = 1;

    public LoadMoreScrollListener(LinearLayoutManager layoutManager) {
        mLayoutManager = layoutManager;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        int itemCount = mLayoutManager.getItemCount();
        int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();

        if (isLoading && previousTotalItemCount < itemCount) {
            isLoading = false;
            previousTotalItemCount = itemCount;
        }

        if (!isLoading && newState == RecyclerView.SCROLL_STATE_IDLE
                && lastVisibleItemPosition + visibleThreshold >= itemCount) {
            isLoading = true;
            onLoadMore(++currentPage);
        }
    }

    public abstract void onLoadMore(int page);
}
