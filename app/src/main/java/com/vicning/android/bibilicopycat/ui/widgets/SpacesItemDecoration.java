package com.vicning.android.bibilicopycat.ui.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vicning.android.bibilicopycat.ui.adapters.RecommendPageAdapter;

/**
 * Created by Neil on 2016/8/7.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public SpacesItemDecoration(int space) {
        this.mSpace = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int childAdapterPosition = parent.getChildAdapterPosition(view);
        if (childAdapterPosition == 0) {
            outRect.bottom = mSpace;
        } else {
            int temp = childAdapterPosition % 6;
            switch (temp) {
                case 1:
                    if (childAdapterPosition == 1) {
                        outRect.top = 0;
                    }
                    outRect.top = mSpace;
                    outRect.left = mSpace;
                    outRect.right = mSpace;
                    outRect.bottom = mSpace;
                    break;

                case 2:
                    outRect.left = mSpace;
                    outRect.right = mSpace / 2;
                    outRect.bottom = mSpace;
                    break;

                case 3:
                    outRect.left = mSpace / 2;
                    outRect.right = mSpace;
                    outRect.bottom = mSpace;
                    break;

                case 4:
                    outRect.left = mSpace;
                    outRect.right = mSpace / 2;
                    outRect.bottom = mSpace;
                    break;

                case 5:
                    outRect.left = mSpace / 2;
                    outRect.right = mSpace;
                    outRect.bottom = mSpace;
                    break;

                case 0:
                    outRect.top = mSpace;
                    outRect.left = mSpace;
                    outRect.right = mSpace;
                    outRect.bottom = mSpace;
                    break;
            }
        }
//        int itemViewType = parent.getAdapter().getItemViewType(childAdapterPosition);
//        switch (itemViewType) {
//            case RecommendPageAdapter.BANNER:
//                outRect.bottom = mSpace;
//                break;
//
//            case RecommendPageAdapter.SECTION_HEADER:
//                outRect.left = mSpace;
//                outRect.right = mSpace;
//                outRect.bottom = mSpace;
//                break;
//
//            case RecommendPageAdapter.SECTION_FOOTER:
//                outRect.left = mSpace;
//                outRect.right = mSpace;
//                outRect.bottom = mSpace;
//                break;
//
//            case RecommendPageAdapter.STANDARD_CARD:
//                int pos = childAdapterPosition % 6;
//
//                break;
//        }


//        outRect.left = mSpace;
//        outRect.right = mSpace;
//        outRect.bottom = mSpace;
//        // Add top margin only for the first item to avoid double space between items
//        if (parent.getChildAdapterPosition(view) == 0)
//            outRect.top = mSpace;
    }
}
