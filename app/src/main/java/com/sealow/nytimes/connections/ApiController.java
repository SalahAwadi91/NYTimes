package com.sealow.nytimes.connections;



import com.sealow.nytimes.generic.MConstants;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiController {


    private static ApiController mInstance;
    private Retrofit mRetrofit;
    private Apis mApiMethods;

    /**
     * This method apply singleton pattern for get shared HttpHelper instance.
     *
     * @return HttpHelper
     */
    public static synchronized ApiController getInstance() {
        if (mInstance == null) {
            mInstance = new ApiController();
        }
        return mInstance;
    }

    /**
     * Use constructor for init {@link Retrofit} object with custom {@link OkHttpClient}.
     */
    private ApiController() {
        mRetrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(MConstants.BASE_URL)
                .client(getUnsafeOkHttpClient().build())
                .build();
    }

    /**
     * This method for get api methods.
     *
     * @return ApiMethods
     */
    public Apis getApiMethods() {
        if (mApiMethods == null) {
            mApiMethods = mRetrofit.create(Apis.class);
        }
        return mApiMethods;
    }

    /**
     * Create custom {@link OkHttpClient} for use with {@link Retrofit}
     *
     * @return OkHttpClient
     */
    private OkHttpClient getClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        return builder.build();
    }

    /**
     * For return a {@link Retrofit} instance.
     *
     * @return Retrofit
     */
    public Retrofit getRetrofit() {
        return mRetrofit;
    }


    private  OkHttpClient.Builder getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
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

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
