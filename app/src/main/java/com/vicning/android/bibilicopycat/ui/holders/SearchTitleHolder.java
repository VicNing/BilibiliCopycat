package com.vicning.android.bibilicopycat.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.vicning.android.bibilicopycat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/19.
 */
public class SearchTitleHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_search_title)
    public TextView tvSearchTitle;

    public SearchTitleHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
