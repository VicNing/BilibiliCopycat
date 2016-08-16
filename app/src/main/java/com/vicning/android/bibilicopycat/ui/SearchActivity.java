package com.vicning.android.bibilicopycat.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Neil on 2016/8/16.
 */
public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String search = getIntent().getStringExtra("search");
        Log.e("SEARCH", "onCreate: " + search );
        TextView textView = new TextView(this);
        textView.setText(search);
        setContentView(textView);
    }
}
