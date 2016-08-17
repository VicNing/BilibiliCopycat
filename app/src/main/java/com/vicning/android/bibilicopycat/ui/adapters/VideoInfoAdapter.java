package com.vicning.android.bibilicopycat.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.model.entity.VideoInfo;
import com.vicning.android.bibilicopycat.ui.holders.AuthorInfoHolder;
import com.vicning.android.bibilicopycat.ui.holders.VideoInfoHolder;

/**
 * Created by Neil on 2016/8/14.
 */
public class VideoInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_VIDEO_INFO = 0;
    private static final int TYPE_AUTHOR_INFO = 1;
    private static final int TYPE_VIDEO_TAG = 2;
    private static final int TYPE_VIDEO_RELATIVE = 3;

    private Context mContext;
    private VideoInfo videoInfo;

    public VideoInfoAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_VIDEO_INFO:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_video_info, parent, false);
                return new VideoInfoHolder(view);
            case TYPE_AUTHOR_INFO:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_author_info, parent, false);
                return new AuthorInfoHolder(view);
            default:
                //TODO Actually have not been able to get video tags and video relatives...
                throw new RuntimeException("no such holder");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (videoInfo != null) {
            if (holder instanceof VideoInfoHolder) {
                VideoInfoHolder videoInfoHolder = (VideoInfoHolder) holder;
                videoInfoHolder.tvTitle.setText(videoInfo.title);
                videoInfoHolder.tvPlayCount.setText(videoInfo.play);
                videoInfoHolder.tvReviewCount.setText(videoInfo.review);
                videoInfoHolder.tvVideoDesc.setText(videoInfo.description);
            } else if (holder instanceof AuthorInfoHolder) {
                AuthorInfoHolder authorInfoHolder = (AuthorInfoHolder) holder;
                authorInfoHolder.ivAvatar.setImageURI(videoInfo.face);
                authorInfoHolder.tvName.setText(videoInfo.author);
                authorInfoHolder.tvPostTime.setText(videoInfo.created_at);
            }
        }
    }

    @Override
    public int getItemCount() {
        //TODO Actually have not been able to get video tags and video relatives...
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_VIDEO_INFO;
            case 1:
                return TYPE_AUTHOR_INFO;
            case 2:
                return TYPE_VIDEO_TAG;
            default:
                return TYPE_VIDEO_RELATIVE;
        }
    }

    public void onDataReceived(VideoInfo videoInfo) {
        this.videoInfo = videoInfo;
    }
}
