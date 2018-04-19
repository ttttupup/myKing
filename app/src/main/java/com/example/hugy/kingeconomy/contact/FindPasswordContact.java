package com.example.hugy.kingeconomy.contact;

import com.example.library.base.BasePresenter;
import com.example.library.base.BaseView;

/**
 * Created by hugy on 2018/3/27.
 */

public interface FindPasswordContact {
    interface View extends BaseView {

        /**
         * 发送失败
         * @param msg
         */
        void sendCodeFail(String msg);

        /**
         * 发送成功
         */
        void sendCodeSuccess();
    }

    interface Presenter extends BasePresenter {
        /**
         * 忘记密码发送验证码
         * @param type
         * @param telphone
         */
       void  sendCode( String type, String telphone);
    }


}
