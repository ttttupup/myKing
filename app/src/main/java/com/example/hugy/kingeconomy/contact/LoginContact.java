package com.example.hugy.kingeconomy.contact;

import com.example.library.base.BasePresenter;
import com.example.library.base.BaseView;

/**
 * Created by hugy on 2018/3/16.
 */

public interface LoginContact {
    interface View extends BaseView {

        /**
         * 登陆成功
         */
        void showLoginSuccess();

        /**
         * 登陆失败
         */
        void showLoginError(String errorMsg);
    }

    interface Presenter extends BasePresenter {

        /**
         * 登陆
         */
        void login(String userName, String password);


    }
}
