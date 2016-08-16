package com.vicning.android.bibilicopycat.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vicning.android.bibilicopycat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/14.
 */
public class VideoInfoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_play_count)
    public TextView tvPlayCount;
    @BindView(R.id.tv_review_count)
    public TextView tvReviewCount;
    @BindView(R.id.tv_video_desc)
    public TextView tvVideoDesc;

    public VideoInfoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }


}
