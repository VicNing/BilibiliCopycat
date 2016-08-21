package com.vicning.android.bibilicopycat.model.beans;

import android.support.annotation.Nullable;

import com.vicning.android.bibilicopycat.model.entity.Recommends;

/**
 * Created by Neil on 2016/8/17.
 */
public class RecoBean {

    public String type;
    public Recommends.Head head;
    public Recommends.Body body;
    public int viewType;

    public RecoBean(String type, int viewType, @Nullable Recommends.Head head, @Nullable Recommends.Body body) {
        this.type = type;
        this.viewType = viewType;
        this.head = head;
        this.body = body;
    }
}
