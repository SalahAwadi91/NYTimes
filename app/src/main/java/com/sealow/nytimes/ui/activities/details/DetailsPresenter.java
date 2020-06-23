package com.sealow.nytimes.ui.activities.details;

import android.content.Intent;

import com.sealow.nytimes.base.BasePresenter;
import com.sealow.nytimes.generic.MConstants;
import com.sealow.nytimes.models.HomeModel;
import com.sealow.nytimes.ui.activities.home.HomeContract;

public class DetailsPresenter extends BasePresenter<DetailsContract.View> implements DetailsContract.Actions {


    @Override
    public void onCreate(DetailsContract.View view) {
        super.onCreate(view);
    }



    @Override
    public void checkIntent(Intent intent) {
        HomeModel homeModel = intent.getExtras().getParcelable(MConstants.MAIN_MODEL_KEY);
        if (homeModel != null){
            view.fillData(homeModel);
        }

    }


}
