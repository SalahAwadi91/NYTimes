package com.sealow.nytimes.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.roger.catloadinglibrary.CatLoadingView;
import com.sealow.nytimes.R;
import com.sealow.nytimes.generic.MUtils;
import com.sealow.nytimes.generic.SnackUtil;
import com.sealow.nytimes.generic.ToastUtil;


public class BaseFragment extends Fragment implements BaseContract.View {

    protected Context mContext;
    protected View mRootView;
    private LayoutInflater mInflater;
    private ViewGroup viewGroup;

    private CatLoadingView progressView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        viewGroup = container;


        return super.onCreateView(inflater, container, savedInstanceState);

    }

    protected void setContentView(@LayoutRes int id) {
        mRootView = mInflater.inflate(id, viewGroup, false);
    }

    protected View findViewById(@IdRes int id) {
        if (mRootView == null)
            throw new IllegalArgumentException("mRootView can't be null, please make sure to initialize it");
        return mRootView.findViewById(id);
    }

    protected ViewGroup getRootView() {
        return (ViewGroup) mRootView;
    }

    @Override
    public void showToast(String msg, ToastUtil.ToastTypes type) {
        ToastUtil.getInstance().toast(mContext, msg, type);
    }

    @Override
    public void showToast(@StringRes int resId, ToastUtil.ToastTypes type) {
        ToastUtil.getInstance().toast(mContext, getString(resId), type);
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
        progressView.show(getFragmentManager(), "");
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
