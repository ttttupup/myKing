package com.example.hugy.kingeconomy.contact;

import com.example.library.base.BasePresenter;
import com.example.library.base.BaseView;

/**
 * Created by hugy on 2018/3/19.
 */

public interface AuthenticationContact {

    interface view extends BaseView {

    }

    interface presenter extends BasePresenter {

        /**
         * 认证
         */
        void authentic();
    }
}
