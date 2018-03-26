package com.example.hugy.kingeconomy.contact;

import com.example.hugy.kingeconomy.bean.TestBean;
import com.example.library.base.BasePresenter;
import com.example.library.base.BaseView;

import java.util.List;

/**
 * Created by hugy on 2018/3/13.
 */

public interface TestContact {
    interface view extends BaseView {
        /**
         * 设置数据
         *
         * @param dataList
         */
        void setData();
    }

    interface presenter extends BasePresenter {
        /**
         * 获取数据
         */
        void getData();
    }
}
