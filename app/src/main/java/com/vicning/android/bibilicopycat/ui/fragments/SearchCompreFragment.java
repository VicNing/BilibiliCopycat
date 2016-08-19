package com.vicning.android.bibilicopycat.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.common.Secret;
import com.vicning.android.bibilicopycat.model.beans.SearchCompreBean;
import com.vicning.android.bibilicopycat.model.entity.SearchCompreInfo;
import com.vicning.android.bibilicopycat.network.ApiServices;
import com.vicning.android.bibilicopycat.network.RetrofitClients;
import com.vicning.android.bibilicopycat.ui.adapters.SearchCompreAdapter;
import com.vicning.android.bibilicopycat.ui.widgets.LoadMoreScrollListener;
import com.vicning.android.bibilicopycat.ui.widgets.SearchCompreItemDecoration;
import com.vicning.android.bibilicopycat.utils.DensityUtil;
import com.vicning.android.bibilicopycat.utils.MD5Util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


/**
 * Created by Neil on 2016/8/18.
 */
public class SearchCompreFragment extends Fragment {

    private String searchKey;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    SearchCompreAdapter adapter;

    public static SearchCompreFragment newInstance(String searchKey) {
        SearchCompreFragment fragment = new SearchCompreFragment();
        Bundle args = new Bundle();
        args.putString("searchKey", searchKey);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchKey = getArguments().getString("searchKey");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_compre, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        adapter = new SearchCompreAdapter(getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        SearchCompreItemDecoration decoration = new SearchCompreItemDecoration(
                DensityUtil.dip2px(getContext(), 10));
        recyclerView.addItemDecoration(decoration);
        recyclerView.addOnScrollListener(new LoadMoreScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page) {
                requestData(page);
            }
        });

        //initial request
        requestData(1);
    }

    private TreeMap<String, String> genarateQueryMap(String page) {
        TreeMap<String, String> queryMap = new TreeMap<>();
        try {
            String encode = URLEncoder.encode(searchKey, "utf-8");

            queryMap.put("appkey", Secret.appKey);
            queryMap.put("keyword", searchKey);
            queryMap.put("main_ver", "v3");
            queryMap.put("page", page);
            queryMap.put("pagesize", "20");
            String sign = MD5Util.string2MD5("appkey=" + Secret.appKey
                    + "&keyword=" + encode
                    + "&main_ver=" + "v3"
                    + "&page=" + page
                    + "&pagesize=" + "20"
                    + Secret.appSecret);
            queryMap.put("sign", sign);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return queryMap;
    }

    public void requestData(final int page) {
        ApiServices apiServices = RetrofitClients.getRetrofitAPI().create(ApiServices.class);
        apiServices.getSearchCompreInfo(genarateQueryMap(String.valueOf(page)))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SearchCompreInfo>() {
                    @Override
                    public void call(SearchCompreInfo searchCompreInfo) {
                        if (page == 1) {
                            adapter.onDataReceived(searchCompreInfo);
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter.onLoadMore(searchCompreInfo);
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throw new RuntimeException(throwable);
                    }
                });
    }
}
