package com.sealow.nytimes.ui.activities.splash;


import android.os.Bundle;

import com.sealow.nytimes.R;
import com.sealow.nytimes.base.BaseActivity;
import com.sealow.nytimes.ui.activities.home.HomeActivity;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    private SplashContract.Actions actions;

    @Override
    protected boolean hasToolBar() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        mContext = this;
        actions = new SplashPresenter();
        actions.setContext(mContext);
        actions.onCreate(this);
    }

    @Override
    public void startHomeActivity() {
        finish();
        start(HomeActivity.class);
    }
}