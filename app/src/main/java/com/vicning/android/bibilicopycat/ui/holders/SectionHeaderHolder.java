package com.vicning.android.bibilicopycat.ui.holders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vicning.android.bibilicopycat.R;

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
    @BindView(R.id.tv_head_ladder)
    public TextView tvHeadLadder;
    @BindView(R.id.tv_head_more)
    public TextView tvHeadMore;

    private Context mContext;

    public SectionHeaderHolder(Context context, View itemView) {
        super(itemView);

        ButterKnife.bind(this, itemView);

    }


}
