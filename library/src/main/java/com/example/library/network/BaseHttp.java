package com.example.library.network;

/**
 * Created by hugy on 2018/3/13.
 */

public interface BaseHttp {

    <T> T createService(String baseUrl, Class<T> serviceClazz);
}
