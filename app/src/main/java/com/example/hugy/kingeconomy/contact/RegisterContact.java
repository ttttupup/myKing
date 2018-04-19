package com.example.hugy.kingeconomy.contact;

import com.example.library.base.BasePresenter;
import com.example.library.base.BaseView;

/**
 * 注册
 * Created by hugy on 2018/3/27.
 */

public interface RegisterContact {

    interface View extends BaseView {
        void sendCodeSuccess();
        void sendCodeFail(String msg);
    }
    interface Presenter extends BasePresenter {
        /**
         * 发送验证码
         * @param type
         * @param telphone
         */
        void sendCode( String type, String telphone);
    }
}
