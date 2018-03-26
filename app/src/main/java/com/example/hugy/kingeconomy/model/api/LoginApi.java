package com.example.hugy.kingeconomy.model.api;

import com.example.hugy.kingeconomy.bean.TestBean;

import io.reactivex.Observable;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by hugy on 2018/3/16.
 */

public interface LoginApi {
    /**
     * 登陆
     * @return
     */
    @FormUrlEncoded
    @POST("admin/test/test?test=aaa")
    Observable<TestBean> login();

    /**
     * 注册
     * @return
     */
    @FormUrlEncoded
    @POST("admin/test/test?test=aaa")
    Observable<TestBean> register();


    /**
     * 找回密码
     * @return
     */
    @FormUrlEncoded
    @POST("admin/test/test?test=aaa")
    Observable<TestBean> findPassword();
}
