package com.example.hugy.kingeconomy.presenter;

import com.example.hugy.kingeconomy.Constant.UrlConstant;
import com.example.hugy.kingeconomy.bean.AuthentictionBean;
import com.example.hugy.kingeconomy.contact.AuthenticationContact;
import com.example.hugy.kingeconomy.model.api.AuthentictionApi;
import com.example.library.base.BasePresenterImpl;
import com.example.library.network.BaseHttp;
import com.example.library.network.BaseHttpImpl;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
        mHttp = new BaseHttpImpl(UrlConstant.BASE_URL);
    }

    @Override
    public void authentic() {

        mHttp.getRetrofit().create(AuthentictionApi.class).authentic()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> addDisposable(disposable))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AuthentictionBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(AuthentictionBean authentictionBean) {

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
