package com.vicning.android.bibilicopycat.ui.fragments;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.common.Secret;
import com.vicning.android.bibilicopycat.model.entity.VideoInfo;
import com.vicning.android.bibilicopycat.network.ApiServices;
import com.vicning.android.bibilicopycat.network.RetrofitClients;
import com.vicning.android.bibilicopycat.ui.adapters.VideoInfoAdapter;
import com.vicning.android.bibilicopycat.ui.widgets.DividerItemDecoration;
import com.vicning.android.bibilicopycat.utils.MD5Util;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static com.vicning.android.bibilicopycat.utils.LogUtil.logError;

/**
 * Created by Neil on 2016/8/14.
 */
public class VideoInfoFragment extends Fragment {

    private String aid;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static VideoInfoFragment newInstance(String aid) {
        VideoInfoFragment fragment = new VideoInfoFragment();
        Bundle args = new Bundle();
        args.putString("aid", aid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aid = getArguments().getString("aid");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_info, container, false);
        ButterKnife.bind(this, view);

        final VideoInfoAdapter adapter = new VideoInfoAdapter(getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        DividerItemDecoration decoration = new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        ApiServices apiServices = RetrofitClients.getRetrofitAPI().create(ApiServices.class);
        apiServices.getVideoInfo(generateQueryMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<VideoInfo>() {
                    @Override
                    public void call(VideoInfo videoInfo) {
                        adapter.onDataReceived(videoInfo);
                        adapter.notifyDataSetChanged();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {

                    }
                });
        return view;
    }

    private HashMap<String, String> generateQueryMap() {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("appkey", Secret.appKey);
        queryMap.put("id", aid);
        queryMap.put("page", "1");
        String sign = MD5Util.string2MD5("appkey=" + Secret.appKey
                + "&id=" + aid
                + "&page=" + "1"
                + Secret.appSecret);
        queryMap.put("sign", sign);

        return queryMap;
    }
}
