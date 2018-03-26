package com.example.hugy.kingeconomy.presenter;

import com.example.hugy.kingeconomy.Constant.UrlConstant;
import com.example.hugy.kingeconomy.bean.TestBean;
import com.example.hugy.kingeconomy.contact.LoginContact;
import com.example.hugy.kingeconomy.model.api.Api;
import com.example.hugy.kingeconomy.model.api.LoginApi;
import com.example.library.base.BasePresenterImpl;
import com.example.library.encryption.XORUtils;
import com.example.library.network.BaseHttp;
import com.example.library.network.BaseHttpImpl;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hugy on 2018/3/16.
 */

public class LoginPresenter extends BasePresenterImpl<LoginContact.view> implements LoginContact.presenter {
    private BaseHttp mHttp;

    public LoginPresenter(LoginContact.view view) {
        super(view);
    }

    @Override
    public void start() {
        mHttp = new BaseHttpImpl(UrlConstant.BASE_URL);
    }

    @Override
    public void login() {
        mHttp.getRetrofit().create(LoginApi.class).login()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    addDisposable(disposable);
                    System.out.println("======qq======");
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("======1======");
                    }

                    @Override
                    public void onNext(TestBean testBean) {
                        System.out.println("======2======");
                        System.out.println(testBean.getRet());
                        System.out.println(testBean.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("======3======");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("======4======");
                    }
                });
    }

    @Override
    public void register() {
        mHttp.getRetrofit().create(LoginApi.class).register()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(disposable -> {
                    addDisposable(disposable);
                    view.showLoadingDialog("qq");
                    System.out.println("======qq======");
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("======1======");
                    }

                    @Override
                    public void onNext(TestBean testBean) {
                        System.out.println("======2======");
                        System.out.println(testBean.getRet());
                        System.out.println(testBean.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("======3======");
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("======4======");
                    }
                });
    }

    @Override
    public void findPassword() {
        mHttp.getRetrofit().create(LoginApi.class).findPassword().subscribeOn(Schedulers.io())
                .doOnSubscribe(x -> addDisposable(x)).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<TestBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TestBean testBean) {

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
