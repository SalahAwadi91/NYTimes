package com.sealow.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class MMediaMetaData implements Parcelable {

    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    protected MMediaMetaData(Parcel in) {
        url = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(url);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MMediaMetaData> CREATOR = new Creator<MMediaMetaData>() {
        @Override
        public MMediaMetaData createFromParcel(Parcel in) {
            return new MMediaMetaData(in);
        }

        @Override
        public MMediaMetaData[] newArray(int size) {
            return new MMediaMetaData[size];
        }
    };


}
