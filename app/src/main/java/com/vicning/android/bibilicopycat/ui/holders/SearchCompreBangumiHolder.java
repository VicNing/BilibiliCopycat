package com.vicning.android.bibilicopycat.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vicning.android.bibilicopycat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/19.
 */
public class SearchCompreBangumiHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_cover)
    public SimpleDraweeView ivCover;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_counts)
    public TextView tvCounts;
    @BindView(R.id.tv_detail)
    public TextView tvDetail;

    public SearchCompreBangumiHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
