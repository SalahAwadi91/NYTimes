package com.sealow.nytimes.connections;

import android.content.Context;
import android.os.AsyncTask;

import com.sealow.nytimes.generic.MConstants;
import com.sealow.nytimes.generic.MUtils;
import com.sealow.nytimes.generic.MySSLSocketFactory;
import com.sealow.nytimes.interfaces.CallEvents;
import com.sealow.nytimes.models.HomeModel;
import com.sealow.nytimes.ui.activities.home.HomeContract;

import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.json.JSONException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;


public class HttpsCall extends AsyncTask<Void , Void ,String> {

    private CallEvents callEvents;

    public HttpsCall( CallEvents callEvents) {
        this.callEvents = callEvents;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {
            return getResult();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }



    @Override
    protected void onPostExecute(String resp) {
        super.onPostExecute(resp);
        if (resp.equals("Error")){
            callEvents.onFauiler("Error While connecting to url");
        }else {
            try {
                List<HomeModel> mList = MUtils.parseResponse(resp);
                if (mList == null){
                    callEvents.onFauiler("Null list");
                }else
                callEvents.onSuccess(mList);
            } catch (JSONException e) {
                e.printStackTrace();
                callEvents.onFauiler(e.getLocalizedMessage());
            }
        }
    }







    private String getResult() throws Exception{
        final TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                }
        };
//        InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
//        Certificate ca;
//        try {
//            ca = cf.generateCertificate(caInput);
//            System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
//        } finally {
//            caInput.close();
//        }
//
// Create a KeyStore containing our trusted CAs
//        String keyStoreType = KeyStore.getDefaultType();
//        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
//        keyStore.load(null, null);
//        keyStore.setCertificateEntry("ca", ca);

// Create a TrustManager that trusts the CAs in our KeyStore
//        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//        tmf.init(keyStore);

// Create an SSLContext that uses our TrustManager
//        SSLContext context = SSLContext.getInstance("TLS");
//        context.init(null, tmf.getTrustManagers(), null);

// Tell the URLConnection to use a SocketFactory from our SSLContext
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null,trustAllCerts,
                new java.security.SecureRandom());



        URL url = new URL(MConstants.BASE_URL);
        HttpsURLConnection urlConnection =
                (HttpsURLConnection)url.openConnection();
        urlConnection.setSSLSocketFactory(sc.getSocketFactory());
        InputStream in = urlConnection.getInputStream();
        BufferedReader inBuffer = new BufferedReader(new InputStreamReader(in));
        String decodedString;
        StringBuilder stringBuilder = new StringBuilder();
        while ((decodedString = inBuffer.readLine()) != null) {
            stringBuilder.append(decodedString);
        }
        System.out.println(stringBuilder.toString());
        in.close();
        return stringBuilder.toString();

    }
}
