package com.vicning.android.bibilicopycat.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.model.beans.RecoBean;
import com.vicning.android.bibilicopycat.model.entity.Recommends;
import com.vicning.android.bibilicopycat.ui.PortraitPlayerActivity;
import com.vicning.android.bibilicopycat.ui.holders.BannerHolder;
import com.vicning.android.bibilicopycat.ui.holders.LongCardHolder;
import com.vicning.android.bibilicopycat.ui.holders.SectionFooterHolder;
import com.vicning.android.bibilicopycat.ui.holders.SectionHeaderHolder;
import com.vicning.android.bibilicopycat.ui.holders.StandardCardHolder;

import java.util.ArrayList;

/**
 * Created by Neil on 2016/8/16.
 */
public class RecommendPageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_BANNER = 0;
    public static final int TYPE_SECTION_HEADER = 1;
    public static final int TYPE_SECTION_FOOTER = 2;
    public static final int TYPE_STANDARD_CARD = 3;
    public static final int TYPE_LONG_CARD = 4;

    private Context mContext;
    private ArrayList<RecoBean> dataList;

    public RecommendPageAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case TYPE_BANNER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_banner, parent, false);
                return new BannerHolder(view);
            case TYPE_SECTION_HEADER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_section_header, parent, false);
                return new SectionHeaderHolder(mContext, view);
            case TYPE_SECTION_FOOTER:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_section_footer, parent, false);
                return new SectionFooterHolder(view);
            case TYPE_STANDARD_CARD:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_standard_card, parent, false);
                return new StandardCardHolder(mContext, view);
            case TYPE_LONG_CARD:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_long_card, parent, false);
                return new LongCardHolder(view);
            default:
                throw new RuntimeException("No such holder");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (dataList != null) {

            if (holder instanceof SectionHeaderHolder) {
                onBindSectionHeaderHolder(holder, position);

            } else if (holder instanceof StandardCardHolder) {
                onBindStandardCardHolder(holder, position);

            } else if (holder instanceof LongCardHolder) {
                onBindLongCardHolder(holder, position);

            }
        }
    }

    private void onBindSectionHeaderHolder(RecyclerView.ViewHolder holder, int position) {
        RecoBean recoBean = dataList.get(position - 1);
        SectionHeaderHolder sectionHeaderHolder = (SectionHeaderHolder) holder;
        switch (recoBean.head.title) {
            case "热门焦点":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_header_hot);
                break;

            case "番剧推荐":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_bangumi);
                break;

            case "热门直播":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_head_live);
                break;

            case "动画区":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_anim);
                break;

            case "音乐区":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_music);
                break;

            case "舞蹈区":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_dance);
                break;

            case "游戏区":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_game);
                break;

            case "鬼畜区":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_guichu);
                break;

            case "科技区":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_tech);
                break;

            case "活动中心":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_header_activity_center);
                break;

            case "生活区":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_life);
                break;

            case "时尚区":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_fashion);
                break;

            case "娱乐区":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_entertain);
                break;

            case "电视剧区":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_soap);
                break;

            case "电影区":
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_category_movie);
                break;

            default:
                sectionHeaderHolder.ivIcon.setImageResource(R.drawable.ic_header_topic);
                break;
        }
        if (recoBean.type.equals("weblink")) {
            sectionHeaderHolder.tvDesc.setText("话题");
        } else {
            sectionHeaderHolder.tvDesc.setText(recoBean.head.title);
        }
        if (recoBean.type.equals("recommend")) {
            sectionHeaderHolder.tvHeadLadder.setVisibility(View.VISIBLE);
            sectionHeaderHolder.tvHeadMore.setVisibility(View.GONE);
        } else {
            sectionHeaderHolder.tvHeadLadder.setVisibility(View.GONE);
            sectionHeaderHolder.tvHeadMore.setVisibility(View.VISIBLE);
        }
    }

    private void onBindStandardCardHolder(RecyclerView.ViewHolder holder, int position) {
        final Recommends.Body body = dataList.get(position - 1).body;
        StandardCardHolder standardCardHolder = (StandardCardHolder) holder;
        standardCardHolder.ivCardPic.setImageURI(body.cover);
        standardCardHolder.tvCardDesc.setText(body.title);
        standardCardHolder.tvPlayCounts.setText(body.play);
        standardCardHolder.tvCommentCounts.setText(body.danmaku);
        standardCardHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PortraitPlayerActivity.class);
                intent.putExtra("aid", body.param);
                mContext.startActivity(intent);
            }
        });
    }

    private void onBindLongCardHolder(RecyclerView.ViewHolder holder, int position) {
        Recommends.Body body = dataList.get(position - 1).body;
        LongCardHolder longCardHolder = (LongCardHolder) holder;
        longCardHolder.ivCardPic.setImageURI(body.cover);
        if (TextUtils.isEmpty(body.title)) {
            longCardHolder.tvCardDesc.setVisibility(View.GONE);
        } else {
            longCardHolder.tvCardDesc.setVisibility(View.VISIBLE);
            longCardHolder.tvCardDesc.setText(body.title);
        }
    }

    @Override
    public int getItemCount() {
        if (dataList != null) {
            return dataList.size() + 1;
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (dataList != null) {
            if (position == 0) {
                return TYPE_BANNER;
            } else {
                return dataList.get(position - 1).viewType;
            }
        }
        return 0;
    }

    public int getSpanSize(int position) {
        switch (getItemViewType(position)) {
            case TYPE_STANDARD_CARD:
                return 1;
            default:
                return 2;
        }
    }

    public void onDataReceived(Recommends data) {
        dataList = new ArrayList<>();

        for (Recommends.Result result : data.result) {
            String type = result.type;
            dataList.add(new RecoBean(type, TYPE_SECTION_HEADER, result.head, null));
            for (Recommends.Body body : result.body) {
                if (type.equals("weblink") || type.equals("activity")) {
                    dataList.add(new RecoBean(type, TYPE_LONG_CARD, null, body));
                } else {
                    dataList.add(new RecoBean(type, TYPE_STANDARD_CARD, null, body));
                }
            }
            if (!type.equals("weblink") && !type.equals("activity")) {
                dataList.add(new RecoBean(type, TYPE_SECTION_FOOTER, result.head, null));
            }
        }
    }
}
