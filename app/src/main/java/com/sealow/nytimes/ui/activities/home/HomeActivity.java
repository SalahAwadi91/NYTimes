package com.sealow.nytimes.ui.activities.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sealow.nytimes.R;
import com.sealow.nytimes.base.BaseActivity;
import com.sealow.nytimes.generic.MConstants;
import com.sealow.nytimes.interfaces.AdapterEvents;
import com.sealow.nytimes.models.HomeModel;
import com.sealow.nytimes.ui.activities.details.DetailsActivity;
import com.sealow.nytimes.ui.activities.splash.SplashContract;
import com.sealow.nytimes.ui.activities.splash.SplashPresenter;
import com.sealow.nytimes.ui.adapters.HomeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends BaseActivity implements HomeContract.View , AdapterEvents {

    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @BindView(R.id.rvHome)
    RecyclerView rvHome;

    private HomeContract.Actions actions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();

    }



    private void init() {
        mContext = this;
        ButterKnife.bind(this);
        rvHome.setLayoutManager(new LinearLayoutManager(mContext));

        actions = new HomePresenter();
        actions.setContext(mContext);
        actions.onCreate(this);
        actions.onGetMostPopular("1");

    }




    @Override
    public void notifyAdapter(List<HomeModel> homeModelList) {
        rvHome.setAdapter(new HomeAdapter(mContext , homeModelList , this));
    }

    @Override
    public void onItemClicked(Object object) {

        Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
        intent.putExtra(MConstants.MAIN_MODEL_KEY, (HomeModel) object);
        startActivity(intent);
    }
}