package com.vicning.android.bibilicopycat.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.common.SectionHeaderMap;
import com.vicning.android.bibilicopycat.model.entity.Recommends;
import com.vicning.android.bibilicopycat.ui.PortraitPlayerActivity;
import com.vicning.android.bibilicopycat.ui.holders.BannerHolder;
import com.vicning.android.bibilicopycat.ui.holders.SectionFooterHolder;
import com.vicning.android.bibilicopycat.ui.holders.SectionHeaderHolder;
import com.vicning.android.bibilicopycat.ui.holders.StandardCardHolder;

/**
 * Created by Neil on 2016/8/6.
 */
public class RecommendPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /*
    * 0.Banner
    * 1.热门
    * 2.直播
    * 3.番剧
    * 4.动画
    * 5.音乐
    * 6.舞蹈
    * 7.游戏
    * 8.鬼畜
    * 9.科技
    * 10.生活
    * 11.时尚
    * 12.娱乐
    * 13.电视剧
    * 14.电影
    * */

    public static final int BANNER = 0;
    public static final int SECTION_HEADER = 1;
    public static final int SECTION_FOOTER = 2;
    public static final int STANDARD_CARD = 3;
    public static final int LONG_CARD = 4;

    private Context mContext;
    private Recommends mRecommends;

    public RecommendPageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case BANNER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_banner, parent, false);
                return new BannerHolder(view);
            case SECTION_HEADER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_section_header, parent, false);
                return new SectionHeaderHolder(mContext, view);
            case SECTION_FOOTER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_section_footer, parent, false);
                return new SectionFooterHolder(view);
            case STANDARD_CARD:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_standard_card, parent, false);
                return new StandardCardHolder(mContext, view);
            default:
                throw new RuntimeException("No such holder");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (mRecommends != null) {
            if (holder instanceof SectionHeaderHolder) {
                SectionHeaderHolder sectionHeaderHolder = (SectionHeaderHolder) holder;
                sectionHeaderHolder.tvDesc.setText(SectionHeaderMap.headers[(position - 1) / 6]);

            } else if (holder instanceof StandardCardHolder) {
                Recommends.Result result = getResult(position);
                Recommends.Body body = null;
                switch (position % 6) {
                    case 2:
                        body = result.body.get(0);
                        break;
                    case 3:
                        body = result.body.get(1);
                        break;
                    case 4:
                        body = result.body.get(2);
                        break;
                    case 5:
                        body = result.body.get(3);
                        break;
                }
                StandardCardHolder standardCardHolder = (StandardCardHolder) holder;
                standardCardHolder.ivCardPic.setImageURI(body.cover);
                standardCardHolder.tvCardDesc.setText(body.title);
                standardCardHolder.tvPlayCounts.setText(body.play);
                standardCardHolder.tvCommentCounts.setText(body.danmaku);
                final Recommends.Body finalBody = body;
                standardCardHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, PortraitPlayerActivity.class);
                        intent.putExtra("aid", finalBody.param);
                        mContext.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        //Banner + （14个分区，每个分区占用6个元素）
        return 1 + 14 * 6;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return BANNER;
        } else if (position % 6 - 1 == 0) {
            return SECTION_HEADER;
        } else if (position % 6 == 0) {
            return SECTION_FOOTER;
        } else {
            return STANDARD_CARD;
        }
    }

    public int getSpanSize(int position) {
        switch (getItemViewType(position)) {
            case BANNER:
            case SECTION_HEADER:
            case SECTION_FOOTER:
                return 2;
            case STANDARD_CARD:
                return 1;
        }
        return 1;
    }

    public void onDataReceived(Recommends data) {
        mRecommends = data;
    }

    public Recommends.Result getResult(int position) {
        int resultIndex = (position - (position % 6)) / 6;
        Recommends.Result result = null;
        switch (resultIndex) {
            case 0:
                result = mRecommends.result.get(0);
                break;
            case 1:
                result = mRecommends.result.get(1);
                break;
            case 2:
                result = mRecommends.result.get(2);
                break;
            case 3:
                result = mRecommends.result.get(5);
                break;
            case 4:
                result = mRecommends.result.get(6);
                break;
            case 5:
                result = mRecommends.result.get(7);
                break;
            case 6:
                result = mRecommends.result.get(8);
                break;
            case 7:
                result = mRecommends.result.get(10);
                break;
            case 8:
                result = mRecommends.result.get(12);
                break;
            case 9:
                result = mRecommends.result.get(14);
                break;
            case 10:
                result = mRecommends.result.get(15);
                break;
            case 11:
                result = mRecommends.result.get(16);
                break;
            case 12:
                result = mRecommends.result.get(17);
                break;
            case 13:
                result = mRecommends.result.get(19);
                break;
        }
        return result;
    }
}
