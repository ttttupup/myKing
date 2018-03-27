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
        void showLoginError();
    }

    interface Presenter extends BasePresenter {

        /**
         * 登陆
         */
        void login();

        /**
         * 注册
         */
        void register();

        void findPassword();
    }
}
