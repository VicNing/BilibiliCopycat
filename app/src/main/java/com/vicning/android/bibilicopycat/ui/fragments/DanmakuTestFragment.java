package com.vicning.android.bibilicopycat.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.ui.PortraitPlayerActivity;
import com.vicning.android.bibilicopycat.ui.TestActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/11.
 */
public class DanmakuTestFragment extends Fragment {

    @BindView(R.id.et_danmaku_test)
    EditText etDanmakuTest;
    @BindView(R.id.bt_danmaku_test)
    Button btDanmakuTest;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danmaku_test, container, false);
        ButterKnife.bind(this, view);

        btDanmakuTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PortraitPlayerActivity.class);
                String text = etDanmakuTest.getText().toString();
                Log.e("Direct AID", "onClick: " + text);
                intent.putExtra("aid", text);
                startActivity(intent);
            }
        });

        return view;
    }
}
