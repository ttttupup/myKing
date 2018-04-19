package com.example.hugy.kingeconomy.presenter;

import com.example.hugy.kingeconomy.constant.ErrorCodeConstant;
import com.example.hugy.kingeconomy.constant.UrlConstant;
import com.example.hugy.kingeconomy.bean.UserInfoBean;
import com.example.hugy.kingeconomy.common.BaseResponse;
import com.example.hugy.kingeconomy.contact.LoginContact;
import com.example.hugy.kingeconomy.model.api.LoginApi;
import com.example.library.base.BasePresenterImpl;
import com.example.library.network.BaseHttp;
import com.example.library.network.BaseHttpImpl;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import retrofit2.Response;

/**
 * Created by hugy on 2018/3/16.
 */

public class LoginPresenter extends BasePresenterImpl<LoginContact.View> implements LoginContact.Presenter {
    private BaseHttp mHttp;

    public LoginPresenter(LoginContact.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();
        mHttp = new BaseHttpImpl();
    }

    @Override
    public void login(String userName, String password) {
        mHttp.createService(UrlConstant.BASE_URL, LoginApi.class).login(userName, password)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    addDisposable(disposable);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BaseResponse<UserInfoBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Logger.i("订阅");
                    }

                    @Override
                    public void onNext(Response<BaseResponse<UserInfoBean>> response) {
                        Logger.i("发送验证码请求成功");
                        if (null == response) {
                            mView.showLoginError("网络请求失败");
                            return;
                        }
                        Logger.i("响应码", response.code());
                        if (ErrorCodeConstant.HTTTP_SUCCESS != response.code()) {
                            mView.showLoginError("网络请求失败");
                            return;
                        }
                        BaseResponse<UserInfoBean> body = response.body();
                        if (null == body) {
                            mView.showLoginError("网络请求失败");
                            return;
                        }
                        if (ErrorCodeConstant.SUCCESS.equals(body.getCode())) {
                            UserInfoBean data = body.getData();
                            Realm realm = Realm.getDefaultInstance();
                            if (null != data) {
                                realm.beginTransaction();
//                                realm.delete(UserInfoBean.class);
                                realm.deleteAll();
                                realm.copyToRealmOrUpdate(data);
                                realm.commitTransaction();
                            }
                            mView.showLoginSuccess();
                        } else {
                            mView.showLoginError(body.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e,"");
                        mView.showLoginError("网络请求失败");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
