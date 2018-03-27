package com.example.hugy.kingeconomy.presenter;

import com.example.hugy.kingeconomy.Constant.UrlConstant;
import com.example.hugy.kingeconomy.bean.TestBean;
import com.example.hugy.kingeconomy.contact.TestContact;
import com.example.hugy.kingeconomy.model.api.Api;
import com.example.library.base.BasePresenterImpl;
import com.example.library.network.BaseHttp;
import com.example.library.network.BaseHttpImpl;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by hugy on 2018/3/13.
 */

public class TestPresenter extends BasePresenterImpl<TestContact.view> implements TestContact.presenter {
    private BaseHttp mHttp;

    public TestPresenter(TestContact.view view) {
        super(view);

    }

    @Override
    public void start() {
        mHttp = new BaseHttpImpl(UrlConstant.BASE_URL);
    }

    @Override
    public void getData() {
        mHttp.getRetrofit().create(Api.class).getData()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        addDisposable(disposable);
                        System.out.println("======qq======");
                    }
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
}
