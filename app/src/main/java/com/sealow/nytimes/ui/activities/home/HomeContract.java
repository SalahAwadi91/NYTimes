package com.sealow.nytimes.ui.activities.home;

import com.sealow.nytimes.base.BaseContract;
import com.sealow.nytimes.models.HomeModel;
import com.sealow.nytimes.ui.activities.splash.SplashContract;

import java.util.List;

public interface HomeContract {

    interface Actions extends BaseContract.Actions<HomeContract.View> {
        void onGetMostPopular(String daysBefore);
    }

    interface View extends BaseContract.View {
        void notifyAdapter(List<HomeModel> homeModelList);
    }
}
