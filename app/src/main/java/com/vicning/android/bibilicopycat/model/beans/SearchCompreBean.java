package com.vicning.android.bibilicopycat.model.beans;


import android.support.annotation.Nullable;

import com.vicning.android.bibilicopycat.model.entity.SearchCompreInfo;

/**
 * Created by Neil on 2016/8/19.
 */
public class SearchCompreBean {
    public int viewType;
    public SearchCompreInfo.Video_ video;
    public SearchCompreInfo.Bangumi_ bangumi;

    public SearchCompreBean(int viewType,
                            @Nullable SearchCompreInfo.Video_ video,
                            @Nullable SearchCompreInfo.Bangumi_ bangumi) {
        this.viewType = viewType;
        this.video = video;
        this.bangumi = bangumi;
    }
}
