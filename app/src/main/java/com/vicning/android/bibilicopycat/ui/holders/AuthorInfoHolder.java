package com.vicning.android.bibilicopycat.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vicning.android.bibilicopycat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/14.
 */
public class AuthorInfoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_avatar)
    public SimpleDraweeView ivAvatar;
    @BindView(R.id.tv_name)
    public TextView tvName;
    @BindView(R.id.tv_post_time)
    public TextView tvPostTime;

    public AuthorInfoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
