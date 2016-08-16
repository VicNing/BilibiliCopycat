package com.vicning.android.bibilicopycat.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/6.
 */
public class BannerHolder extends RecyclerView.ViewHolder {
    public BannerHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
}
