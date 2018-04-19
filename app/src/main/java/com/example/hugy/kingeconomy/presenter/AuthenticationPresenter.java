package com.example.hugy.kingeconomy.presenter;

import com.example.hugy.kingeconomy.constant.ErrorCodeConstant;
import com.example.hugy.kingeconomy.constant.UrlConstant;
import com.example.hugy.kingeconomy.bean.AuthentictionBean;
import com.example.hugy.kingeconomy.common.BaseResponse;
import com.example.hugy.kingeconomy.contact.AuthenticationContact;
import com.example.hugy.kingeconomy.model.api.AuthentictionApi;
import com.example.library.base.BasePresenterImpl;
import com.example.library.network.BaseHttp;
import com.example.library.network.BaseHttpImpl;
import com.orhanobut.logger.Logger;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import retrofit2.Response;

/**
 * Created by hugy on 2018/3/19.
 */

public class AuthenticationPresenter extends BasePresenterImpl<AuthenticationContact.view> implements AuthenticationContact.presenter {
    private BaseHttp mHttp;

    public AuthenticationPresenter(AuthenticationContact.view view) {
        super(view);
    }

    @Override
    public void start() {
        mHttp = new BaseHttpImpl();
    }

    @Override
    public void authentic(String uuid, String realName, String storeCode) {

        mHttp.createService(UrlConstant.BASE_URL, AuthentictionApi.class).authentic(uuid, realName, storeCode)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> addDisposable(disposable))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BaseResponse<AuthentictionBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<BaseResponse<AuthentictionBean>> response) {
                        Logger.i("无编码认证请求成功");
                        if (null == response) {
                            mView.showError();
                            return;
                        }
                        Logger.i("响应码" + response.code());
                        BaseResponse<AuthentictionBean> body = response.body();
                        if (null == body) {
                            mView.showError();
                            return;
                        }
                        if (ErrorCodeConstant.SUCCESS.equals(body.getCode())) {
                            AuthentictionBean bean = body.getData();
                            mView.authenticationSuccess(bean.getRealtorStatus());
                        } else {
                            mView.authenticationFail(body.getMsg());
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

    @Override
    public void authenticNoCode(String uuid, MultipartBody.Part front, MultipartBody.Part back) {
        mHttp.createService(UrlConstant.BASE_URL, AuthentictionApi.class).authenticNoCode(uuid, front, back)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> addDisposable(disposable))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<BaseResponse<AuthentictionBean>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Response<BaseResponse<AuthentictionBean>> response) {
                        Logger.i("有编码认证请求成功");
                        if (null == response) {
                            mView.showError();
                            return;
                        }
                        Logger.i("响应码" + response.code());
                        BaseResponse<AuthentictionBean> body = response.body();
                        if (null == body) {
                            mView.showError();
                            return;
                        }
                        if (ErrorCodeConstant.SUCCESS.equals(body.getCode())) {
                            AuthentictionBean bean = body.getData();
                            mView.authenticationSuccess(bean.getRealtorStatus());
                        } else {
                            mView.authenticationFail(body.getMsg());
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
