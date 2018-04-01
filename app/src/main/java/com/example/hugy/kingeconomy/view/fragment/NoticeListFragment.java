package com.example.hugy.kingeconomy.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.NoticeListBean;
import com.example.hugy.kingeconomy.view.adapter.CommonItemDecoration;
import com.example.hugy.kingeconomy.view.adapter.NoticeListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoticeListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoticeListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticeListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NoticeListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoticeListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoticeListFragment newInstance(String param1, String param2) {
        NoticeListFragment fragment = new NoticeListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflateView = inflater.inflate(R.layout.fragment_notice_list, container, false);
        
        RecyclerView noticeList = inflateView.findViewById(R.id.rv_notice_list);
        List<NoticeListBean> list = new ArrayList<NoticeListBean>();
        list.add(new NoticeListBean("网络游戏迎转型升级 改变成取胜关键","近年来，随着国内游戏产业高速发展，内容形式不断\n" +
                "丰富，以网络游戏为游戏为游戏为游戏代表的新文..."));
        list.add(new NoticeListBean("网络游戏迎转型升级","随着国内游戏产业高速发展"));
        list.add(new NoticeListBean("网络游戏迎转型升级","以网络游戏为游戏为游戏为游戏代表的新文"));
        noticeList.setAdapter(new NoticeListAdapter(list));
        noticeList.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        noticeList.addItemDecoration(new CommonItemDecoration(container.getContext(), R.drawable.shape_recy_gray_30));
        return inflateView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
