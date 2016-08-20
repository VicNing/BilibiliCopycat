package com.vicning.android.bibilicopycat.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.ui.adapters.SearchPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/16.
 */
public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tv_search_key)
    TextView tvSearchKey;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);

        getWindow().getDecorView().setBackgroundColor(
                ContextCompat.getColor(this, R.color.colorWindowBackground));

        initView();


    }

    private void initView() {
        String search = getIntent().getStringExtra("search");
        SearchPagerAdapter adapter = new SearchPagerAdapter(
                getSupportFragmentManager(), this, search);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        ibBack.setOnClickListener(this);
        tvSearchKey.setText(search);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                finish();
                break;
        }
    }
}
