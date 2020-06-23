package com.sealow.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MainModel implements Parcelable {

    private List<HomeModel> results;


    protected MainModel(Parcel in) {
        results = in.createTypedArrayList(HomeModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(results);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MainModel> CREATOR = new Creator<MainModel>() {
        @Override
        public MainModel createFromParcel(Parcel in) {
            return new MainModel(in);
        }

        @Override
        public MainModel[] newArray(int size) {
            return new MainModel[size];
        }
    };

    public List<HomeModel> getResults() {
        return results;
    }

    public void setResults(List<HomeModel> results) {
        this.results = results;
    }
}
