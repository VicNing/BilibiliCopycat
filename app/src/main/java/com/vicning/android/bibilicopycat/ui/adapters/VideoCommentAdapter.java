package com.vicning.android.bibilicopycat.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.model.entity.VideoComment;
import com.vicning.android.bibilicopycat.ui.holders.HotDividerHolder;
import com.vicning.android.bibilicopycat.ui.holders.VideoCommentHolder;

/**
 * Created by Neil on 2016/8/15.
 */
public class VideoCommentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_COMMENT = 0;
    public static final int TYPE_HOT_DIVIDER = 1;
    public static final int[] LEVEL_RESOURCES = new int[]{R.drawable.ic_lv0,
            R.drawable.ic_lv1,
            R.drawable.ic_lv2,
            R.drawable.ic_lv3,
            R.drawable.ic_lv4,
            R.drawable.ic_lv5,
            R.drawable.ic_lv6,
            R.drawable.ic_lv7,
            R.drawable.ic_lv8,
            R.drawable.ic_lv9};

    private Context mContext;
    private VideoComment videoComment;

    public VideoCommentAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_COMMENT:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_video_comment, parent, false);
                return new VideoCommentHolder(view);
            case TYPE_HOT_DIVIDER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_hot_divider, parent, false);
                return new HotDividerHolder(view);
            default:
                throw new RuntimeException("no such holder");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (videoComment != null) {
            if (holder instanceof VideoCommentHolder) {
                onBindVideoCommentHolder(holder, position);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (videoComment != null) {
            return videoComment.data.hots.size() + videoComment.data.replies.size() + 1;
        } else {
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (videoComment != null) {
            if (position == videoComment.data.hots.size()) {
                return TYPE_HOT_DIVIDER;
            } else {
                return TYPE_COMMENT;
            }
        } else {
            return TYPE_COMMENT;
        }
    }

    public void onDataReceived(VideoComment comment) {
        this.videoComment = comment;
    }

    private void onBindVideoCommentHolder(RecyclerView.ViewHolder holder, int position) {
        VideoCommentHolder videoCommentHolder = (VideoCommentHolder) holder;
        if (position < videoComment.data.hots.size()) {
            VideoComment.Hot hot = videoComment.data.hots.get(position);
            videoCommentHolder.ivAvatar.setImageURI(
                    hot.member.avatar);
            if (hot.member.sex.equals("男")) {
                videoCommentHolder.ivGender.setVisibility(View.VISIBLE);
                videoCommentHolder.ivGender.setImageResource(R.drawable.ic_user_male);
            } else if (hot.member.sex.equals("女")) {
                videoCommentHolder.ivGender.setVisibility(View.VISIBLE);
                videoCommentHolder.ivGender.setImageResource(R.drawable.ic_user_female);
            } else {
                videoCommentHolder.ivGender.setVisibility(View.GONE);
            }
            videoCommentHolder.tvAuthorname.setText(hot.member.uname);
            videoCommentHolder.tvFloor.setText("#" + hot.floor);
            videoCommentHolder.tvPostTime.setText(hot.ctime + "");
            videoCommentHolder.ivLevel.setImageResource(
                    LEVEL_RESOURCES[hot.member.level_info.current_level]);
            videoCommentHolder.tvComment.setText(hot.content.message);
        } else {
            int truePosition = position - videoComment.data.hots.size() - 1;
            VideoComment.Reply reply = videoComment.data.replies.get(truePosition);
            videoCommentHolder.ivAvatar.setImageURI(
                    reply.member.avatar);
            if (reply.member.sex.equals("男")) {
                videoCommentHolder.ivGender.setVisibility(View.VISIBLE);
                videoCommentHolder.ivGender.setImageResource(R.drawable.ic_user_male);
            } else if (reply.member.sex.equals("女")) {
                videoCommentHolder.ivGender.setVisibility(View.VISIBLE);
                videoCommentHolder.ivGender.setImageResource(R.drawable.ic_user_female);
            } else {
                videoCommentHolder.ivGender.setVisibility(View.GONE);
            }
            videoCommentHolder.tvAuthorname.setText(reply.member.uname);
            videoCommentHolder.tvFloor.setText("#" + reply.floor);
            videoCommentHolder.tvPostTime.setText(reply.ctime + "");
            videoCommentHolder.ivLevel.setImageResource(
                    LEVEL_RESOURCES[reply.member.level_info.current_level]);
            videoCommentHolder.tvComment.setText(reply.content.message);
        }
    }

    public void onLoadMore(VideoComment more) {
        if (videoComment != null) {
            int replies = more.data.replies.size();
            if (replies != 0) {
                videoComment.data.replies.addAll(more.data.replies);
                notifyItemRangeInserted(getItemCount(), replies);
            }
        }
    }
}
