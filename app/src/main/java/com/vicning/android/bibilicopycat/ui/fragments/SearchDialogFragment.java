package com.vicning.android.bibilicopycat.ui.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vicning.android.bibilicopycat.R;
import com.vicning.android.bibilicopycat.ui.SearchActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Neil on 2016/8/16.
 */
public class SearchDialogFragment extends DialogFragment implements View.OnClickListener,
        TextView.OnEditorActionListener {

    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.ib_search)
    ImageButton ibSearch;
    @BindView(R.id.ib_clear)
    ImageButton ibClear;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_dialog, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ibBack.setOnClickListener(this);
        ibSearch.setOnClickListener(this);
        ibClear.setOnClickListener(this);
        etSearch.setOnEditorActionListener(this);
        etSearch.requestFocus();
    }

    @Override
    public void onResume() {
        //Resizing dialog to 95% of screen width.
        //need to post it because
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Window window = getDialog().getWindow();
                Point size = new Point();
                Display display = window.getWindowManager().getDefaultDisplay();
                display.getSize(size);
                window.setLayout((int) (size.x * 0.95), WindowManager.LayoutParams.WRAP_CONTENT);
                window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
            }
        });

        super.onResume();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //Make dialog with no titlebar, and appears on top of the screen.
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.setGravity(Gravity.TOP);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        return dialog;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back:
                getDialog().dismiss();
                break;

            case R.id.ib_clear:
                etSearch.setText("");
                break;

            case R.id.ib_search:
                performSearch();
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //IME search: show search key on soft keyboard
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            performSearch();
            return true;
        }
        return false;
    }

    public void performSearch() {
        Intent intent = new Intent(getContext(), SearchActivity.class);
        intent.putExtra("search", etSearch.getText().toString());
        startActivity(intent);
        getDialog().dismiss();
    }
}
