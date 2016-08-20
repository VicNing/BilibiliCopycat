package com.vicning.android.bibilicopycat.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.model.beans.SearchCompreBean;
import com.vicning.android.bibilicopycat.model.entity.SearchCompreInfo;
import com.vicning.android.bibilicopycat.ui.PortraitPlayerActivity;
import com.vicning.android.bibilicopycat.ui.holders.SearchCompreBangumiHolder;
import com.vicning.android.bibilicopycat.ui.holders.SearchCompreVideoHolder;
import com.vicning.android.bibilicopycat.ui.holders.SearchTitleHolder;

import java.util.ArrayList;

/**
 * Created by Neil on 2016/8/18.
 */
public class SearchCompreAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private ArrayList<SearchCompreBean> searchCompreData;
    private int numResults;

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_BANGUMI = 1;
    public static final int TYPE_VIDEO = 2;

    public SearchCompreAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_TITLE:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_seach_title, parent, false);
                return new SearchTitleHolder(view);

            case TYPE_BANGUMI:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_search_compre_bangumi, parent, false);
                return new SearchCompreBangumiHolder(view);

            case TYPE_VIDEO:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_search_compre_video, parent, false);
                return new SearchCompreVideoHolder(view);

            default:
                throw new RuntimeException("No such holder.");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (searchCompreData != null) {
            if (holder instanceof SearchTitleHolder) {
                SearchTitleHolder searchTitleHolder = (SearchTitleHolder) holder;
                searchTitleHolder.tvSearchTitle.setText(String.format(
                        mContext.getResources().getString(R.string.search_title),
                        numResults));
            } else if (holder instanceof SearchCompreBangumiHolder) {
                SearchCompreBangumiHolder bangumiHolder = (SearchCompreBangumiHolder) holder;
                SearchCompreInfo.Bangumi_ bangumi = searchCompreData.get(position).bangumi;
                bangumiHolder.ivCover.setImageURI(bangumi.cover);
                bangumiHolder.tvTitle.setText(bangumi.title);
                bangumiHolder.tvCounts.setText(String.format(
                        mContext.getResources().getString(R.string.search_counts),
                        bangumi.play_count,
                        bangumi.favorites));
                if (bangumi.is_finish == 0) {
                    bangumiHolder.tvDetail.setText(String.format(
                            mContext.getResources().getString(R.string.search_newest_ep),
                            bangumi.newest_ep_index));
                } else {
                    bangumiHolder.tvDetail.setText("完结");
                }
            } else if (holder instanceof SearchCompreVideoHolder) {
                SearchCompreVideoHolder videoHolder = (SearchCompreVideoHolder) holder;
                final SearchCompreInfo.Video_ video = searchCompreData.get(position).video;

                //压缩Cover为360x225，避免占用过多内存
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(video.pic))
                        .setResizeOptions(new ResizeOptions(360, 225))
                        .build();
                PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                        .setOldController(videoHolder.ivCover.getController())
                        .setImageRequest(request)
                        .build();
                videoHolder.ivCover.setController(controller);

                videoHolder.tvTitle.setText(video.title);
                videoHolder.tvAuthor.setText(video.author);
                videoHolder.tvPlayCounts.setText(video.play + "");
                videoHolder.tvCommentCounts.setText(video.review + "");
                videoHolder.rlVideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PortraitPlayerActivity.class);
                        intent.putExtra("aid", video.aid);
                        mContext.startActivity(intent);
                    }
                });
            }
        }

    }

    @Override
    public int getItemCount() {
        if (searchCompreData != null) {
            return searchCompreData.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (searchCompreData != null) {
            return searchCompreData.get(position).viewType;
        }
        return 0;
    }

    public void onDataReceived(SearchCompreInfo searchCompreInfo) {
        searchCompreData = new ArrayList<>();
        for (SearchCompreInfo.Bangumi_ bangumi : searchCompreInfo.result.bangumi) {
            searchCompreData.add(new SearchCompreBean(TYPE_BANGUMI, null, bangumi));
        }

        searchCompreData.add(new SearchCompreBean(TYPE_TITLE, null, null));

        for (SearchCompreInfo.Video_ video : searchCompreInfo.result.video) {
            searchCompreData.add(new SearchCompreBean(TYPE_VIDEO, video, null));
        }

        numResults = searchCompreInfo.pageinfo.video.numResults;
    }

    public void onLoadMore(SearchCompreInfo searchCompreInfo) {
        if (searchCompreData != null) {
            int size = searchCompreInfo.result.video.size();
            if (size != 0) {
                for (SearchCompreInfo.Video_ video_ : searchCompreInfo.result.video) {
                    searchCompreData.add(new SearchCompreBean(TYPE_VIDEO, video_, null));
                }
                notifyItemRangeInserted(getItemCount(), size);
            }
        }
    }

}
