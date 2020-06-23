package com.sealow.nytimes.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.roger.catloadinglibrary.CatLoadingView;
import com.sealow.nytimes.R;
import com.sealow.nytimes.generic.MUtils;
import com.sealow.nytimes.generic.SnackUtil;
import com.sealow.nytimes.generic.ToastUtil;


public abstract class BaseActivity extends AppCompatActivity implements BaseContract.View  {



    protected Context mContext;

    private CatLoadingView progressView;
    private ViewGroup rootView;

    protected abstract boolean hasToolBar();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (hasToolBar()){
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

        }

    }


    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        rootView = findViewById(R.id.root_view);

        if (rootView == null) {
            throw new NullPointerException("Set root_view id to root xml element");
        }
    }

    protected ViewGroup getRootView() {
        return rootView;
    }

    @Override
    public void showToast(String msg, ToastUtil.ToastTypes type) {
        ToastUtil.getInstance().toast(this, msg, type);
    }

    @Override
    public void showToast(@StringRes int resId, ToastUtil.ToastTypes type) {
        ToastUtil.getInstance().toast(this, getString(resId), type);
    }

    @Override
    public void showSnackBar(String msg, SnackUtil.SnackTypes type, View.OnClickListener... onClickListeners) {
        SnackUtil.getInstance().snack(getRootView(), msg, type, onClickListeners);
    }

    @Override
    public void showSnackBar(@StringRes int resId, SnackUtil.SnackTypes type, View.OnClickListener... onClickListeners) {
        SnackUtil.getInstance().snack(getRootView(), getString(resId), type, onClickListeners);
    }

    @Override
    public void showProgress() {
        if (progressView == null){
            progressView = new CatLoadingView();
            progressView.setCanceledOnTouchOutside(false);
            progressView.setCancelable(false);
        }
        progressView.show(getSupportFragmentManager(), "");
    }

    @Override
    public void hideProgress() {
        if (progressView != null){
            progressView.dismiss();
        }
    }

    @Override
    public void checkInternet() {
        if (mContext != null && !MUtils.isNetworkAvailable(mContext)) {
            showSnackBar(R.string.noInternet, SnackUtil.SnackTypes.FAILED);
        }
    }



    @Override
    public void start(Class mClass){
        if (mContext == null){
            throw new IllegalArgumentException("Context can not be empty");
        }
        startActivity(new Intent(mContext , mClass));

    }





}
