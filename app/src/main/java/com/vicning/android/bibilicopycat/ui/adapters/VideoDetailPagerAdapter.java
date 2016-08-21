package com.vicning.android.bibilicopycat.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vicning.android.bibilicopycat.ui.fragments.VideoCommentFragment;
import com.vicning.android.bibilicopycat.ui.fragments.VideoInfoFragment;

/**
 * Created by Neil on 2016/8/14.
 */
public class VideoDetailPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String mAid;

    public VideoDetailPagerAdapter(FragmentManager fm, Context context, String aid) {
        super(fm);
        mContext = context;
        mAid = aid;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return VideoInfoFragment.newInstance(mAid);

            case 1:
                return VideoCommentFragment.newInstance(mAid);

            default:
                throw new RuntimeException("no such fragment");
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "简介";
            case 1:
                return "评论";
            default:
                return null;
        }
    }
}
