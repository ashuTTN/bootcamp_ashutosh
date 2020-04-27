package com.example.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static GetDataService service;
    private static final String baseUrl = "https://storage.googleapis.com/network-security-conf-codelab.appspot.com/v2/";


    public static Retrofit getRetrofitInstance(){
        if (retrofit==null){

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static GetDataService getDataService() {
        if (service == null) {
            service = getRetrofitInstance().create(GetDataService.class);

        }
        return service;
    }
}
