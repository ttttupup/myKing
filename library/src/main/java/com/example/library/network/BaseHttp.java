package com.example.library.network;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by hugy on 2018/3/13.
 */

public interface BaseHttp {
    Retrofit getRetrofit();

    OkHttpClient.Builder setInterceptor(Interceptor interceptor);

    Retrofit.Builder setConverterFactory(Converter.Factory factory);
}
