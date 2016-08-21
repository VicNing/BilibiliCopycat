package com.vicning.android.bibilicopycat.ui.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.vicning.android.bibilicopycat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/7.
 */
public class SectionFooterHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.rl_footer)
    public RelativeLayout rlFooter;

    public SectionFooterHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
