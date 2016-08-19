package com.vicning.android.bibilicopycat.ui.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vicning.android.bibilicopycat.ui.fragments.SearchCompreFragment;
import com.vicning.android.bibilicopycat.ui.fragments.TestFragment;

/**
 * Created by Neil on 2016/8/18.
 */
public class SearchPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String searchKey;
    private static final String[] pageTitles = new String[]{"综合", "番剧", "专题", "UP主"};

    public SearchPagerAdapter(FragmentManager fm, Context context, String searchKey) {
        super(fm);
        mContext = context;
        this.searchKey = searchKey;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return SearchCompreFragment.newInstance(searchKey);
        }
        return TestFragment.newInstance(searchKey);
    }

    @Override
    public int getCount() {
        return pageTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
