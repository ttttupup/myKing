package com.example.hugy.kingeconomy.presenter;

import com.example.hugy.kingeconomy.contact.RegisterContact;
import com.example.library.base.BasePresenterImpl;

/**
 * Created by hugy on 2018/3/27.
 */

public class RegisterPresenter  extends BasePresenterImpl<RegisterContact.View>implements RegisterContact.Presenter{
    public RegisterPresenter(RegisterContact.View view) {
        super(view);
    }
}
