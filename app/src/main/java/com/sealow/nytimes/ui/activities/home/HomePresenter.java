package com.sealow.nytimes.ui.activities.home;

import com.sealow.nytimes.R;
import com.sealow.nytimes.base.BasePresenter;
import com.sealow.nytimes.connections.ApiController;
import com.sealow.nytimes.connections.HttpCallUtil;
import com.sealow.nytimes.connections.HttpsCall;
import com.sealow.nytimes.generic.DialogUtils;
import com.sealow.nytimes.generic.ToastUtil;
import com.sealow.nytimes.interfaces.CallEvents;
import com.sealow.nytimes.interfaces.RxCallback;
import com.sealow.nytimes.models.HomeModel;
import com.sealow.nytimes.models.MainModel;

import java.util.List;

import io.reactivex.Observable;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Actions{


    @Override
    public void onCreate(HomeContract.View view) {
        super.onCreate(view);

        //todo
        // note: this code  before i know i can use libs
        //view.showProgress();
//        new HttpsCall(new CallEvents() {
//            @Override
//            public void onSuccess(Object response) {
//                view.hideProgress();
//                try {
//                    view.notifyAdapter((List<HomeModel>) response);
//                } catch (Exception e) {
//                    view.showToast(e.getLocalizedMessage() , ToastUtil.ToastTypes.FAILED);
//                }
//            }
//
//            @Override
//            public void onFauiler(String error) {
//                view.hideProgress();
//            }
//        }).execute();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onGetMostPopular(String daysBefore) {
        view.showProgress();
        Observable<MainModel> purposesObservable = ApiController.getInstance()
                .getApiMethods().getMostPopular(daysBefore);
        HttpCallUtil.getInstance().newOneCall(new RxCallback() {
            @Override
            public void onFailed(String error) {
                view.hideProgress();
                DialogUtils.showBottomSheet(mContext, mContext.getString(R.string.errorTitle)
                        , error, R.raw.erroranim);
            }

            @Override
            public void onNext(Object objects, Observable observables) {
                view.hideProgress();
                try {
                    if (objects instanceof MainModel) {
                        List<HomeModel> homeModelList =  ((MainModel) objects).getResults();
                        if (homeModelList != null && !homeModelList.isEmpty()) {
                            view.notifyAdapter(homeModelList);
                        } else {
                            DialogUtils.showBottomSheet(mContext, mContext.getString(R.string.errorTitle)
                                    , mContext.getString(R.string.NoData), R.raw.erroranim);
                        }
                    }else {
                        DialogUtils.showBottomSheet(mContext, mContext.getString(R.string.errorTitle)
                                , mContext.getString(R.string.NoData), R.raw.erroranim);
                    }
                } catch (Exception e) {
                    DialogUtils.showBottomSheet(mContext, mContext.getString(R.string.errorTitle)
                            , e.getLocalizedMessage() , R.raw.erroranim);
                }

            }

            @Override
            public void onNext(List<Object> objects, Observable... observables) {

            }

            @Override
            public void onComplete() {

            }
        }, purposesObservable);
    }
}
