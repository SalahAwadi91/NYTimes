package com.sealow.nytimes.ui.activities.details;

import android.content.Intent;

import com.sealow.nytimes.base.BaseContract;
import com.sealow.nytimes.models.HomeModel;
import com.sealow.nytimes.ui.activities.home.HomeContract;

import java.util.List;

public interface DetailsContract {
    interface Actions extends BaseContract.Actions<DetailsContract.View> {
        void checkIntent(Intent intent);
    }

    interface View extends BaseContract.View {
        void fillData(HomeModel homeModelList);
    }
}
