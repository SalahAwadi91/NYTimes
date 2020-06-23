package com.sealow.nytimes.ui.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.sealow.nytimes.R;
import com.sealow.nytimes.base.BaseActivity;
import com.sealow.nytimes.generic.MConstants;
import com.sealow.nytimes.models.HomeModel;
import com.sealow.nytimes.ui.activities.home.HomeContract;
import com.sealow.nytimes.ui.activities.home.HomePresenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity implements DetailsContract.View {


    @Override
    protected boolean hasToolBar() {
        return true;
    }

    @BindView(R.id.imgItem)
    ImageView imgItem;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.txtDesc)
    TextView txtDesc;
    @BindView(R.id.txtDate)
    TextView txtDate;
    @BindView(R.id.txtExtraDesc)
    TextView txtExtraDesc;
    @BindView(R.id.txtDoneBy)
    TextView txtDoneBy;

    private DetailsContract.Actions actions;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        init();
    }

    private void init() {
        mContext = this;
        ButterKnife.bind(this);
        actions = new DetailsPresenter();
        actions.setContext(mContext);
        actions.onCreate(this);


        actions.checkIntent(getIntent());

    }

    @Override
    public void fillData(HomeModel homeModelList) {

        try {
            Picasso.get().load(homeModelList.getMedia().get(0).getMediaMetaData().get(2).getUrl()).into(imgItem);
        } catch (Exception e) {
            Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/thumb/f/f0/Error.svg/1200px-Error.svg.png").into(imgItem);
        }

        txtTitle.setText(homeModelList.getTitle());
        txtDesc.setText(homeModelList.getSource());
        txtDate.setText(homeModelList.getPublished_date());
        txtExtraDesc.setText(homeModelList.getAbstracts());
        txtDoneBy.setText(homeModelList.getByline());
    }
}