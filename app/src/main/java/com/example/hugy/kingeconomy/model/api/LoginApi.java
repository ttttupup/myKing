package com.example.hugy.kingeconomy.model.api;

import com.example.hugy.kingeconomy.bean.UserInfoBean;
import com.example.hugy.kingeconomy.common.BaseResponse;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hugy on 2018/3/16.
 */

public interface LoginApi {
    /**
     * 登陆
     *
     * @return
     */
    @FormUrlEncoded
    @POST("app/login.api")
    Observable<Response<BaseResponse<UserInfoBean>>> login(@Field("username") String userName,
                                                           @Field("password") String password);

}
