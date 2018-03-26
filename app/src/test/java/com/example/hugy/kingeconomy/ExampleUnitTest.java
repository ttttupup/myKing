package com.example.hugy.kingeconomy;

import org.junit.Test;

import java.util.Observable;
import java.util.UUID;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.flowable.FlowableOnErrorReturn;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {
        System.out.println(UUID.randomUUID().toString());
        String [] a= new String[]{"1","2"};
        io.reactivex.Observable.just(a)
                .map(new Function<String[], Object>() {
                    @Override
                    public Object apply(String[] strings) throws Exception {
                        strings[0]="a";
                        strings[1]="b";
                        return strings;
                    }
                })
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                                String [] c= (String[]) o;
                        System.out.println(c[0]);
                        System.out.println(c[1]);
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