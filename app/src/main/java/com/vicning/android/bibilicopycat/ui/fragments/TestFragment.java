package com.vicning.android.bibilicopycat.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vicning.android.bibilicopycat.R;

import static com.vicning.android.bibilicopycat.utils.LogUtil.*;


/**
 * Created by Neil on 2016/8/5.
 */
public class TestFragment extends Fragment {

    private String pageTitle;

    public static TestFragment newInstance(String pageTitle) {
        TestFragment fragmentDemo = new TestFragment();
        Bundle args = new Bundle();
        args.putString("pageTitle", pageTitle);
        fragmentDemo.setArguments(args);
        return fragmentDemo;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageTitle = getArguments().getString("pageTitle");
        logError(pageTitle, "onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        logError(pageTitle, "onAttach");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        logError(pageTitle, "onCreateView");
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        final TextView tvTest = (TextView) view.findViewById(R.id.tv_test);
        tvTest.setText(pageTitle);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        logError(pageTitle, "onStart");

    }

    @Override
    public void onResume() {
        super.onResume();
        logError(pageTitle, "onResume");

    }

    @Override
    public void onPause() {
        super.onPause();
        logError(pageTitle, "onPause");

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        logError(pageTitle, "onDestroyView");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logError(pageTitle, "onDestroy");

    }

    @Override
    public void onDetach() {
        super.onDetach();
        logError(pageTitle, "onDetach");
    }
}
