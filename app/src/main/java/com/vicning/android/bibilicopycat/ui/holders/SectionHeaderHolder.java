package com.vicning.android.bibilicopycat.ui.holders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.ui.PortraitPlayerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/7.
 */
public class SectionHeaderHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_icon)
    public ImageView ivIcon;
    @BindView(R.id.tv_desc)
    public TextView tvDesc;
    @BindView(R.id.tv_detail)
    public TextView tvDetail;

    private Context mContext;

    public SectionHeaderHolder(Context context, View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

    }


}
