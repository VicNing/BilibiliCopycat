package com.vicning.android.bibilicopycat.ui.holders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.vicning.android.bibilicopycat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/7.
 */
public class StandardCardHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_card_pic)
    public SimpleDraweeView ivCardPic;
    @BindView(R.id.tv_card_desc)
    public TextView tvCardDesc;
    @BindView(R.id.tv_play_counts)
    public TextView tvPlayCounts;
    @BindView(R.id.tv_comment_counts)
    public TextView tvCommentCounts;
    @BindView(R.id.card_view)
    public CardView cardView;

    private Context mContext;

    public StandardCardHolder(Context context, View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = context;
    }
}
