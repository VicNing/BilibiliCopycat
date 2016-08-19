package com.vicning.android.bibilicopycat.ui.widgets;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vicning.android.bibilicopycat.ui.adapters.RecommendPageAdapter;
import com.vicning.android.bibilicopycat.ui.adapters.SearchCompreAdapter;

/**
 * Created by Neil on 2016/8/19.
 */
public class SearchCompreItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public SearchCompreItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int childAdapterPosition = parent.getChildAdapterPosition(view);
        int itemViewType = parent.getAdapter().getItemViewType(childAdapterPosition);

        switch (itemViewType) {
            case SearchCompreAdapter.TYPE_TITLE:
                if (childAdapterPosition == 0) {
                    outRect.set(mSpace, mSpace, mSpace, mSpace);
                } else {
                    outRect.set(mSpace, 0, mSpace, mSpace);
                }
                break;

            case SearchCompreAdapter.TYPE_BANGUMI:
                if (childAdapterPosition == 0) {
                    outRect.set(mSpace, mSpace, mSpace, mSpace);
                } else {
                    outRect.set(mSpace, 0, mSpace, mSpace);
                }
                break;

            case SearchCompreAdapter.TYPE_VIDEO:
                outRect.set(mSpace, 0, mSpace, mSpace);
                break;
        }
    }
}
