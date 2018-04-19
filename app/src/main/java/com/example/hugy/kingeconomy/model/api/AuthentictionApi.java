package com.example.hugy.kingeconomy.model.api;

import com.example.hugy.kingeconomy.bean.AuthentictionBean;
import com.example.hugy.kingeconomy.common.BaseResponse;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by hugy on 2018/3/20.
 */

public interface AuthentictionApi {
    /**
     * 有编码门店认证
     *
     * @param realName
     * @param storeCode
     * @return
     */
    @FormUrlEncoded
    @POST("sysUser/companyAuthentication")
    Observable<Response<BaseResponse<AuthentictionBean>>> authentic(@Header("uuid") String uuid,
                                                                    @Field("realname") String realName,
                                                                    @Field("storeCode") String storeCode);

    /**
     * 无编码门店认证
     *
     * @return
     */
    @Multipart
    @POST("sysAuthenticationInfo/uploadFile")
    Observable<Response<BaseResponse<AuthentictionBean>>> authenticNoCode(@Header("uuid") String uuid,
                                                                          @Part MultipartBody.Part front,
                                                                          @Part MultipartBody.Part back);
}
