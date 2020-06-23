package com.sealow.nytimes.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MMedia  implements Parcelable {

    @SerializedName("caption")
    private String caption;
    @SerializedName("copyright")
    private String copyright;
    @SerializedName("media-metadata")
    private List<MMediaMetaData> mediaMetaData;


    protected MMedia(Parcel in) {
        caption = in.readString();
        copyright = in.readString();
        mediaMetaData = in.createTypedArrayList(MMediaMetaData.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(caption);
        dest.writeString(copyright);
        dest.writeTypedList(mediaMetaData);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MMedia> CREATOR = new Creator<MMedia>() {
        @Override
        public MMedia createFromParcel(Parcel in) {
            return new MMedia(in);
        }

        @Override
        public MMedia[] newArray(int size) {
            return new MMedia[size];
        }
    };

    public List<MMediaMetaData> getMediaMetaData() {
        return mediaMetaData;
    }

    public void setMediaMetaData(List<MMediaMetaData> mediaMetaData) {
        this.mediaMetaData = mediaMetaData;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }




}
