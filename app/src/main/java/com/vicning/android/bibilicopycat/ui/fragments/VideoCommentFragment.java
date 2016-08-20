package com.vicning.android.bibilicopycat.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.model.entity.VideoComment;
import com.vicning.android.bibilicopycat.network.ApiServices;
import com.vicning.android.bibilicopycat.network.RetrofitClients;
import com.vicning.android.bibilicopycat.ui.adapters.VideoCommentAdapter;
import com.vicning.android.bibilicopycat.ui.widgets.DividerItemDecoration;
import com.vicning.android.bibilicopycat.ui.widgets.LoadMoreScrollListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Neil on 2016/8/14.
 */
public class VideoCommentFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private String aid;
    private VideoCommentAdapter adapter;
    private ApiServices apiServices;

    public static VideoCommentFragment newInstance(String aid) {
        VideoCommentFragment fragment = new VideoCommentFragment();
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

        initView();

        apiServices = RetrofitClients.getRetrofitAPI().create(ApiServices.class);
        //initial request
        requestData(1);

        return view;
    }

    private void initView() {
        //Intial RecyclerView
        adapter = new VideoCommentAdapter(getContext());
        recyclerView.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        DividerItemDecoration decoration = new DividerItemDecoration(
                getContext(), LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(decoration);

        recyclerView.addOnScrollListener(new LoadMoreScrollListener(manager) {
            @Override
            public void onLoadMore(int page) {
                requestData(page);
            }
        });
    }

    private void requestData(final int page) {
        apiServices.getVideoComment(aid, page, 20, 0, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<VideoComment>() {
                    @Override
                    public void call(VideoComment videoComment) {
                        if (page == 1) {
                            adapter.onDataReceived(videoComment);
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter.onLoadMore(videoComment);
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
