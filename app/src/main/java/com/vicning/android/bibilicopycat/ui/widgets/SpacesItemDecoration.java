package com.vicning.android.bibilicopycat.ui.widgets;

import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vicning.android.bibilicopycat.ui.adapters.RecommendPageAdapter;

/**
 * Created by Neil on 2016/8/7.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;
    private GridLayoutManager.SpanSizeLookup spansizeloopup;

    public SpacesItemDecoration(int space, GridLayoutManager.SpanSizeLookup spansizelookUp) {
        this.mSpace = space;
        this.spansizeloopup = spansizelookUp;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int childAdapterPosition = parent.getChildAdapterPosition(view);
        int itemViewType = parent.getAdapter().getItemViewType(childAdapterPosition);

        switch (itemViewType) {
            case RecommendPageAdapter.TYPE_BANNER:
                outRect.set(0, 0, 0, mSpace);
                break;

            case RecommendPageAdapter.TYPE_SECTION_HEADER:
                outRect.set(mSpace, 0, mSpace, mSpace);
                break;

            case RecommendPageAdapter.TYPE_SECTION_FOOTER:
                outRect.set(mSpace, 0, mSpace, mSpace);
                break;

            case RecommendPageAdapter.TYPE_STANDARD_CARD:
                int spanIndex = spansizeloopup.getSpanIndex(childAdapterPosition, 2);
                if (spanIndex == 0) {
                    outRect.set(mSpace, 0, mSpace / 2, mSpace);
                } else {
                    outRect.set(mSpace / 2, 0, mSpace, mSpace);
                }
                break;

            case RecommendPageAdapter.TYPE_LONG_CARD:
                outRect.set(mSpace, 0, mSpace, mSpace);
                break;
        }
    }
}


