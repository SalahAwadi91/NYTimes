package com.sealow.nytimes.ui.activities.splash;

import com.sealow.nytimes.base.BaseContract;

public interface SplashContract {
    interface Actions extends BaseContract.Actions<SplashContract.View> {


    }

    interface View extends BaseContract.View {


        void startHomeActivity();
    }
}
