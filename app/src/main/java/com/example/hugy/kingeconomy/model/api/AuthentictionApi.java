package com.example.hugy.kingeconomy.model.api;

import com.example.hugy.kingeconomy.bean.AuthentictionBean;
import com.example.hugy.kingeconomy.bean.TestBean;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hugy on 2018/3/20.
 */

public interface AuthentictionApi {
    @FormUrlEncoded
    @POST("auth")
    Observable<AuthentictionBean> authentic();
}
