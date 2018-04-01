package com.example.hugy.kingeconomy.view.activity;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.view.adapter.PublicNoticeListAdapter;
import com.example.hugy.kingeconomy.view.fragment.NoticeListFragment;
import com.example.hugy.kingeconomy.view.fragment.PublicNoticeFragment;

public class NoticeActivity extends AppCompatActivity implements NoticeListFragment.OnFragmentInteractionListener, PublicNoticeFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        NoticeListFragment noticeListFragment = new NoticeListFragment();
        PublicNoticeFragment publicNoticeFragment = new PublicNoticeFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.add(R.id.fragment_notice_list, noticeListFragment, "noticeList");
         transaction.add(R.id.fragment_notice_list, publicNoticeFragment, "noticeList");
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
