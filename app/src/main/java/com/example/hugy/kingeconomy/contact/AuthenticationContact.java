package com.example.hugy.kingeconomy.contact;

import com.example.library.base.BasePresenter;
import com.example.library.base.BaseView;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;

/**
 * Created by hugy on 2018/3/19.
 */

public interface AuthenticationContact {

    interface view extends BaseView {
        /**
         * 认证成功
         */
        void authenticationSuccess(String status);

        /**
         * 认证失败
         */
        void authenticationFail(String errorMsg);
    }

    interface presenter extends BasePresenter {

        /**
         * 有编码门店认证
         */
        void authentic(String uuid,String realName,String storeCode);

        /**
         * 无编码门店认证
         */
        void authenticNoCode(String uuid,MultipartBody.Part front,MultipartBody.Part back);
    }
}
