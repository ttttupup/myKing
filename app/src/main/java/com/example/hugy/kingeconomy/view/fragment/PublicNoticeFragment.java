package com.example.hugy.kingeconomy.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.bean.IconBean;
import com.example.hugy.kingeconomy.view.adapter.CommonItemDecoration;
import com.example.hugy.kingeconomy.view.adapter.PublicNoticeListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PublicNoticeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PublicNoticeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PublicNoticeFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PublicNoticeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PublicNoticeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PublicNoticeFragment newInstance(String param1, String param2) {
        PublicNoticeFragment fragment = new PublicNoticeFragment();
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
        View inflate = inflater.inflate(R.layout.fragment_public_notice, container, false);
        RecyclerView recyclerView = inflate.findViewById(R.id.rv_public_notice_list);
        List<IconBean> list = new ArrayList<>();
        list.add(new IconBean("为什么旅行青蛙要住LOFT","阅读数3363","点赞数3366"));
        list.add(new IconBean("择一城终老，遇一人白首，等你陪我在杭州买","阅读数3363","点赞数3366"));
        list.add(new IconBean("择一城终老，遇一人白首","阅读数3363","点赞数3366"));
        PublicNoticeListAdapter adapter = new PublicNoticeListAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new CommonItemDecoration(container.getContext(), R.drawable.shape_recy));
        return inflate;
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
