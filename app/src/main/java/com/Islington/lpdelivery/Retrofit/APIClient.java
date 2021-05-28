package com.Islington.lpdelivery.Retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static final String BASE_URL = "http://kandktechnepal.com/gas_agency/public/api/";
    public static String AppName = "LPDelivery";

    public static Retrofit getRetrofit() {

       HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
       httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
       OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

       Retrofit retrofit = new Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .baseUrl(BASE_URL)
               .client(okHttpClient)
               .build();

       return retrofit;

   }
    public static APIInterface getApiInterface() {
        APIInterface apiInterface = getRetrofit().create(APIInterface.class);
        return apiInterface;
    }
}
