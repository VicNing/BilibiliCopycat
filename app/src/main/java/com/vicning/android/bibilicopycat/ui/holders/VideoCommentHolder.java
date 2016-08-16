package com.vicning.android.bibilicopycat.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vicning.android.bibilicopycat.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/15.
 */
public class VideoCommentHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_avatar)
    public SimpleDraweeView ivAvatar;
    @BindView(R.id.tv_author_name)
    public TextView tvAuthorname;
    @BindView(R.id.iv_gender)
    public ImageView ivGender;
    @BindView(R.id.tv_floor)
    public TextView tvFloor;
    @BindView(R.id.tv_post_time)
    public TextView tvPostTime;
    @BindView(R.id.iv_level)
    public ImageView ivLevel;
    @BindView(R.id.tv_comment)
    public TextView tvComment;

    public VideoCommentHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
