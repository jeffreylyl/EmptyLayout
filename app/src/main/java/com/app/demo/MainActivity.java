package com.app.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.app.emptylayout.EmptyLayout;


public class MainActivity extends AppCompatActivity {

    EmptyLayout mEmptyLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmptyLayout = (EmptyLayout) findViewById(R.id.emptyLayout);

//        tryLoadingDialog();
        mEmptyLayout.setLoadingViewRes(R.layout.common_loading);

        mEmptyLayout.showLoading();

    }

    private void tryLoadingDialog() {
        mEmptyLayout.setLoadingViewRes(R.layout.loading_dialog);
        mEmptyLayout.setLoadingAnimationViewId(R.id.loading_img);

        Animation localAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate_action);
        localAnimation.setInterpolator(new LinearInterpolator());
        mEmptyLayout.setLoadingAnimation(localAnimation);
    }
}
