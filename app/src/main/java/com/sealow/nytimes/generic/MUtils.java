package com.sealow.nytimes.generic;

import android.content.Context;
import android.net.ConnectivityManager;


import com.sealow.nytimes.models.HomeModel;
import com.sealow.nytimes.models.MMedia;
import com.sealow.nytimes.models.MMediaMetaData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class MUtils {

    public static boolean checkInternet(){
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static List<HomeModel>  parseResponse(String resp) throws JSONException {
        JSONObject mainObj = new JSONObject(resp);
        String status = mainObj.getString("status");
//
//        if (status.equals("OK")){
//            List<HomeModel> mList = new ArrayList<>();
//            JSONArray mainResult = mainObj.getJSONArray("results");
//            for (int i = 0; i < mainResult.length(); i++) {
//                HomeModel homeModel = new HomeModel();
//                JSONObject item = mainResult.getJSONObject(i);
//                homeModel.setAbstracts(item.getString("abstract"));
//                homeModel.setTitle(item.getString("title"));
//                homeModel.setByline(item.getString("byline"));
//                homeModel.setType(item.getString("type"));
//                homeModel.setSection(item.getString("section"));
//                homeModel.setSource(item.getString("source"));
//                homeModel.setPublished_date(item.getString("published_date"));
//
//                JSONArray mediaArray = item.getJSONArray("media");
//                if (mediaArray.length() > 0) {
//                JSONObject mediaObj = mediaArray.getJSONObject(0);
//
//                    MMedia mMedia = new MMedia();
//                    mMedia.setCaption(mediaObj.getString("caption"));
//                    mMedia.setCopyright(mediaObj.getString("copyright"));
//
//                    JSONArray mediaImgs = mediaObj.getJSONArray("media-metadata");
//                    MMediaMetaData mMediaMetaData = new MMediaMetaData();
//                    try {
//                        mMediaMetaData.setUrlS(mediaImgs.getJSONObject(0).getString("url"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        mMediaMetaData.setUrlM(mediaImgs.getJSONObject(1).getString("url"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    try {
//                        mMediaMetaData.setUrlL(mediaImgs.getJSONObject(2).getString("url"));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                    mMedia.setMediaMetaData(mMediaMetaData);
//                    homeModel.setMedia(mMedia);
//                }
//
//                mList.add(homeModel);
//
//            }
//            return mList;
//
//            }

        return null;

    }



}
