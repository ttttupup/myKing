package com.example.hugy.kingeconomy.contact;

import com.example.library.base.BasePresenter;
import com.example.library.base.BaseView;

/**
 * Created by hugy on 2018/3/16.
 */

public interface LoginContact {
    interface view extends BaseView {

    }

    interface presenter extends BasePresenter {

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
