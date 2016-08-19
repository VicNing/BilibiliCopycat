package com.vicning.android.bibilicopycat.ui.holders;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.vicning.android.bibilicopycat.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/19.
 */
public class SearchCompreVideoHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_cover)
    public SimpleDraweeView ivCover;
    @BindView(R.id.tv_title)
    public TextView tvTitle;
    @BindView(R.id.tv_author)
    public TextView tvAuthor;
    @BindView(R.id.tv_play_counts)
    public TextView tvPlayCounts;
    @BindView(R.id.tv_comment_counts)
    public TextView tvCommentCounts;
    @BindView(R.id.rl_video)
    public RelativeLayout rlVideo;

    public SearchCompreVideoHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);


    }
}
