package com.sealow.nytimes.ui.activities.splash;

import android.os.Handler;

import com.sealow.nytimes.base.BasePresenter;

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Actions {

    private static final int DELAY_MILLIS = 3000;

    @Override
    public void onCreate(SplashContract.View view) {
        super.onCreate(view);
        new Handler().postDelayed(() -> {
            if (!isDestroyed()){
                view.startHomeActivity();
            }
        },DELAY_MILLIS);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}