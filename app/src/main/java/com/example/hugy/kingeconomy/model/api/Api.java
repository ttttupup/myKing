package com.example.hugy.kingeconomy.model.api;

import com.example.hugy.kingeconomy.bean.TestBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by hugy on 2018/3/15.
 */

public interface Api {
    @GET("admin/test/test?test=aaa")
    Observable<TestBean> getData();
}
