package com.vicning.android.bibilicopycat.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vicning.android.bibilicopycat.ui.fragments.DanmakuTestFragment;
import com.vicning.android.bibilicopycat.ui.fragments.RecommendFragment;
import com.vicning.android.bibilicopycat.ui.fragments.TestFragment;


/**
 * Created by Neil on 2016/8/5.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {
    private static final String[] TAB_TITLES = {"直播", "推荐", "番剧", "分区", "关注", "发现"};
    private Context mContext;

    public MainPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 1) {
            return new RecommendFragment();
        }else if (position == 2) {
            return new DanmakuTestFragment();
        }
        else {
            return TestFragment.newInstance(TAB_TITLES[position]);
        }
    }

    @Override
    public int getCount() {
        return TAB_TITLES.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

}
