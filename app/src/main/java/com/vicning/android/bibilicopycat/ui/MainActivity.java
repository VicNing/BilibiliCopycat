package com.vicning.android.bibilicopycat.ui;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.ui.adapters.MainPagerAdapter;
import com.vicning.android.bibilicopycat.ui.fragments.SearchDialogFragment;


import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the activitie's background color
        getWindow().getDecorView().setBackgroundColor(
                ContextCompat.getColor(this, R.color.colorWindowBackground));

        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //清理图片缓存
        //TODO
        /*ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
        Log.e("onStop", "onStop: clear img cache...");
        imagePipeline.clearCaches();*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                showSearchDialog();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showSearchDialog() {
        FragmentManager mFM = getSupportFragmentManager();
        SearchDialogFragment searchDialogFragment = new SearchDialogFragment();
        searchDialogFragment.show(mFM,"fragment_search");
    }

    private void initView() {
        initToolBar();

        //配置ViewPager并绑定TabLayout
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), this);
        vpMain.setAdapter(adapter);
        tabLayout.setupWithViewPager(vpMain);
        vpMain.setCurrentItem(1);
        vpMain.setOffscreenPageLimit(6);
    }

    /**
     * 初始化ToolBar
     */
    private void initToolBar() {
        //TODO
//        mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        setSupportActionBar(mToolbar);
    }

    /**
     * 获取StatusBar高度
     *
     * @return StatusBar height
     */
    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
