package com.example.library.network;

import android.util.Log;

import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by hugy on 2018/3/13.
 */

public class BaseHttpImpl implements BaseHttp {
    private volatile static Retrofit retrofit;
    private volatile static OkHttpClient okHttpClient;
    private List<Interceptor> interceptors;
    private static final int DEFAULT = 60;// 默认时间


    public Retrofit getRetrofit(String baseUrl) {
        if (retrofit == null) {
            //锁定代码块
            synchronized (BaseHttpImpl.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder()
                                    .setLenient()
                                    .create()
                            ))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(getOkHttpClient())
                            .baseUrl(baseUrl)
                            .build();
                }
            }
        }
        return retrofit;
    }

    public void addInterceptor(Interceptor interceptor) {
        if (null != interceptor) {
            this.interceptors = new ArrayList<>();
            interceptors.add(interceptor);
        }
    }


    /**
     * 获取OkHttpClient实例
     *
     * @return OkHttpClient实例
     */
    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            synchronized (OkHttpClient.class) {// 同步访问
                if (okHttpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
                            .writeTimeout(DEFAULT, TimeUnit.SECONDS)
                            .readTimeout(DEFAULT, TimeUnit.SECONDS)
                            .connectTimeout(DEFAULT, TimeUnit.SECONDS)

                            .addInterceptor(getLoggerInterceptor());

                    if (null != interceptors && interceptors.size() > 0) {
                        for (Interceptor interceptor : interceptors) {
                            builder.addInterceptor(interceptor);
                        }
                    }
                    okHttpClient = builder.build();
                }
            }
        }
        return okHttpClient;
    }

    /**
     * 日志拦截器
     * 将你访问的接口信息
     *
     * @return 拦截器
     */
    public HttpLoggingInterceptor getLoggerInterceptor() {
        //日志显示级别
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.HEADERS;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d("ApiUrl", "--->" + message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

    @Override
    public <T> T createService(String baseUrl, Class<T> serviceClazz) {
        return getRetrofit(baseUrl).create(serviceClazz);
    }
}
