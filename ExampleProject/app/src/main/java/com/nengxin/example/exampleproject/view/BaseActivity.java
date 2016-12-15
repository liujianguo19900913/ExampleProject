package com.nengxin.example.exampleproject.view;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import com.nengxin.example.exampleproject.R;

import de.greenrobot.event.EventBus;

/**
 * Created by alan on 2016/12/1.
 */


public class BaseActivity extends Activity {

    ViewGroup rootView;
    protected View mProgressView;

    public void setContentView(int resId) {
        super.setContentView(resId);
        rootView = (ViewGroup) getWindow().getDecorView();
        initProgressLayout();

    }

    public void setContentView(View view) {

        super.setContentView(view);
        rootView = (ViewGroup) getWindow().getDecorView();
        initProgressLayout();

    }


    protected void initProgressLayout() {
        if (mProgressView == null) {
            mProgressView = getLayoutInflater().inflate(R.layout.loading_layout, rootView
                    , false);

            mProgressView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            setProgressVisible(false);
            rootView.addView(mProgressView);
        }
    }

    public void setProgressVisible(boolean isVisible) {
        if (mProgressView != null) {
            mProgressView.setVisibility(isVisible ? View.VISIBLE : View.GONE);
        }
    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public void setViewDisableDelay(final View view) {
        view.setEnabled(false);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setEnabled(true);
            }
        }, 600);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * an exception if the view doesn't exist.
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int id) {
        T result = (T) findViewById(id);
        if (result == null) {
            throw new IllegalArgumentException("view 0x" + Integer.toHexString(id)
                    + " doesn't exist");
        }
        return result;
    }

}
