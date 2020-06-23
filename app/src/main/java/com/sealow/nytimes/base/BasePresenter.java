package com.sealow.nytimes.base;


import android.content.Context;

import androidx.annotation.CallSuper;

import com.sealow.nytimes.generic.SnackUtil;


public class BasePresenter<V extends BaseContract.View> implements BaseContract.Actions<V> {

    protected V view;
    private boolean destroyed = false;
    protected Context mContext;


    @CallSuper
    @Override
    public void onDestroy() {
        this.view = null;
        destroyed = true;
        SnackUtil.getInstance().dismiss();
    }

    @CallSuper
    @Override
    public void onCreate(V view) {
        this.view = view;
        destroyed = false;

    }


    @CallSuper
    @Override
    public void setContext(Context context){
        mContext = context;
    }

    protected boolean isDestroyed() {
        return destroyed || view == null;
    }



}
