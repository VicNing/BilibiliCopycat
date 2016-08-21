package com.vicning.android.bibilicopycat.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.model.entity.Recommends;
import com.vicning.android.bibilicopycat.network.AppServices;
import com.vicning.android.bibilicopycat.network.RetrofitClients;
import com.vicning.android.bibilicopycat.ui.adapters.RecommendPageAdapter;
import com.vicning.android.bibilicopycat.ui.widgets.SpacesItemDecoration;
import com.vicning.android.bibilicopycat.utils.DensityUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Neil on 2016/8/6.
 */
public class RecommendFragment extends Fragment {

    @BindView(R.id.rv_base)
    RecyclerView rvBase;
    private RecommendPageAdapter recommendPageAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("STATE", "onCreateView: " );
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        ButterKnife.bind(this, view);
        initView();
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView() {

        //当RecyclerView滚动时，暂停加载图片
        rvBase.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Fresco.getImagePipeline().resume();
                } else {
                    Fresco.getImagePipeline().pause();
                }
            }
        });
        recommendPageAdapter = new RecommendPageAdapter(getContext());
        rvBase.setAdapter(recommendPageAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return recommendPageAdapter.getSpanSize(position);
            }
        };
        spanSizeLookup.setSpanIndexCacheEnabled(true);
        gridLayoutManager.setSpanSizeLookup(spanSizeLookup);
        rvBase.setLayoutManager(gridLayoutManager);
        rvBase.addItemDecoration(new SpacesItemDecoration(DensityUtil.dip2px(getContext(), 10)
                , spanSizeLookup));

        initRequest();
    }

    /**
     * 初始化Http请求
     */
    private void initRequest() {
        AppServices apiServices = RetrofitClients.getRetrofitAPP().create(AppServices.class);
        apiServices.getRecommends()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Recommends>() {
                    @Override
                    public void call(Recommends recommends) {
                        recommendPageAdapter.onDataReceived(recommends);
                        recommendPageAdapter.notifyDataSetChanged();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throw new RuntimeException(throwable);
                    }
                });
    }

}
