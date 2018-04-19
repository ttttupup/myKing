package com.example.hugy.kingeconomy.presenter;

import com.example.hugy.kingeconomy.constant.ErrorCodeConstant;
import com.example.hugy.kingeconomy.constant.UrlConstant;
import com.example.hugy.kingeconomy.common.BaseResponse;
import com.example.hugy.kingeconomy.contact.RegisterContact;
import com.example.hugy.kingeconomy.model.api.RegisterApi;
import com.example.library.base.BasePresenterImpl;
import com.example.library.network.BaseHttp;
import com.example.library.network.BaseHttpImpl;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * Created by hugy on 2018/3/27.
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContact.View> implements RegisterContact.Presenter {
    private BaseHttp mHttp;

    public RegisterPresenter(RegisterContact.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();
        mHttp = new BaseHttpImpl();
    }

    @Override
    public void sendCode(String type, String telphone) {
        mHttp.createService(UrlConstant.BASE_URL, RegisterApi.class).sendCode(type, telphone)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    addDisposable(disposable);
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BaseResponse<String>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<BaseResponse<String>> response) {
                        Logger.i("发送验证码请求成功");
                        Logger.i("响应码", response.code());
                        if (ErrorCodeConstant.HTTTP_SUCCESS != response.code()) {
                            mView.sendCodeFail("网络请求失败");
                            return;
                        }
                        BaseResponse<String> body = response.body();
                        if (null == body) {
                            mView.sendCodeFail("网络请求失败");
                            return;
                        }
                        if (ErrorCodeConstant.SUCCESS.equals(body.getCode())) {
                            if ("false".equals(body.getData())) {
                                mView.sendCodeFail("请求失败，请稍后再试");
                            }
                        } else {
                            mView.sendCodeFail(body.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
