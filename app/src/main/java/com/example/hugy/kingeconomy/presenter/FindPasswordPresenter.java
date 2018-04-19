package com.example.hugy.kingeconomy.presenter;


import com.example.hugy.kingeconomy.common.BaseResponse;
import com.example.hugy.kingeconomy.constant.ErrorCodeConstant;
import com.example.hugy.kingeconomy.constant.UrlConstant;
import com.example.hugy.kingeconomy.contact.FindPasswordContact;
import com.example.hugy.kingeconomy.model.api.FindPasswordApi;
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

public class FindPasswordPresenter extends BasePresenterImpl<FindPasswordContact.View> implements FindPasswordContact.Presenter {
    private BaseHttp mHttp;
    public FindPasswordPresenter(FindPasswordContact.View view) {
        super(view);
    }

    @Override
    public void start() {
        super.start();
        mHttp = new BaseHttpImpl();
    }

    @Override
    public void sendCode(String type, String telphone) {
        mHttp.createService(UrlConstant.BASE_URL, FindPasswordApi.class).sendCode(type, telphone)
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
                            mView.showError();
                            return;
                        }
                        BaseResponse<String> body = response.body();
                        if (null == body) {
                            mView.showError();
                            return;
                        }
                        if (ErrorCodeConstant.SUCCESS.equals(body.getCode())) {
                            if ("true".equals(body.getData())) {
                                mView.sendCodeSuccess();
                            }else{
                                mView.sendCodeFail("验证码发送失败");
                            }
                        } else {
                            mView.sendCodeFail(body.getMsg());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(e,"发送验证码失败");
                        mView.showError();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
