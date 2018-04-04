package com.example.hugy.kingeconomy.view.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.hugy.kingeconomy.R;
import com.example.hugy.kingeconomy.view.activity.FindHouseActivity;
import com.example.hugy.kingeconomy.view.activity.ReportActivity;
import com.example.hugy.kingeconomy.view.adapter.CommonItemDecoration;
import com.example.hugy.kingeconomy.view.adapter.RecommendInfoAdapter;
import com.example.hugy.kingeconomy.view.adapter.TaskAdapter;
import com.example.hugy.kingeconomy.view.adapter.TestGridView;
import com.example.hugy.kingeconomy.bean.IconBean;
import com.example.hugy.kingeconomy.bean.RecommendInfo;
import com.example.hugy.kingeconomy.bean.Task;
import com.example.hugy.kingeconomy.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomePageFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomePageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomePageFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SliderLayout banner;

    private OnFragmentInteractionListener mListener;

    public HomePageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomePageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomePageFragment newInstance(String param1, String param2) {
        HomePageFragment fragment = new HomePageFragment();
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
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);
        banner = (SliderLayout) view.findViewById(R.id.sliderLayout);
        HashMap<String, Integer> bannerMap = new HashMap<String, Integer>();
        bannerMap.put("Hannibal", R.mipmap.banner);
        bannerMap.put("Big Bang Theory", R.mipmap.banner_2);
        bannerMap.put("Big Bang Theory", R.mipmap.banner_3);
        for (String name : bannerMap.keySet()) {
            TextSliderView textSliderView = new TextSliderView(container.getContext());
            textSliderView
                    .description(name)
                    .image(bannerMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(slider -> ToastUtils.showToast(container.getContext(), "banner点击事件"));
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);
            banner.addSlider(textSliderView);
        }
        banner.setPresetTransformer(SliderLayout.Transformer.Accordion);
        banner.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        banner.setCustomAnimation(new DescriptionAnimation());
        banner.setDuration(5000);
        banner.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //按钮
        GridView viewById2 = view.findViewById(R.id.gv_icon_function);
        List<IconBean> iconList = new ArrayList<>();
        IconBean icon1 = new IconBean("接任务", R.mipmap.icon_green);
        IconBean icon2 = new IconBean("找楼盘", R.mipmap.icon_orange);
        IconBean icon3 = new IconBean("报备", R.mipmap.icon_blue);
        IconBean icon4 = new IconBean("上客", R.mipmap.icon_red);
        iconList.add(icon1);
        iconList.add(icon2);
        iconList.add(icon3);
        iconList.add(icon4);
        viewById2.setAdapter(new TestGridView(iconList, container.getContext()));
        viewById2.setOnItemClickListener((parent, view1, position, id) -> {
            switch (position) {
                case 0:
                    break;
                case 1:
                    startActivity(new Intent(getActivity(), FindHouseActivity.class));
                    break;
                case 2:
                    startActivity(new Intent(getActivity(), ReportActivity.class));
                    break;
                case 3:
                    break;
            }
        });
        //查询任务
        Task task1 = new Task();
        Task task2 = new Task();
        Task task3 = new Task();
        task1.setAmount("12.00%");
        task1.setName("转发任务");
        task2.setAmount("12.00%");
        task2.setName("预订任务");
        task3.setAmount("12.00%");
        task3.setName("阅读任务");
        List<Task> list = new ArrayList<Task>();
        list.add(task1);
        list.add(task2);
        list.add(task3);
        RecyclerView taskView = view.findViewById(R.id.rv_task);
        taskView.setAdapter(new TaskAdapter(list));
        taskView.addItemDecoration(new CommonItemDecoration(container.getContext(), R.drawable.shape_recy));
        taskView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        //查询推荐
        List<RecommendInfo> list1 = new ArrayList<>();
        RecommendInfo info = new RecommendInfo();
        RecommendInfo info1 = new RecommendInfo();
        info.setAmount("12%");
        info.setImg("");
        info.setName("玉屏山庄");
        info.setPrice("35000元/平");
        info.setType("商铺");
        info1.setAmount("12%");
        info1.setImg("");
        info1.setName("玉屏山庄");
        info1.setPrice("35000元/平");
        info1.setType("商铺");
        list1.add(info);
        list1.add(info1);
        RecyclerView recommendView = view.findViewById(R.id.rv_recommend);
        recommendView.setAdapter(new RecommendInfoAdapter(list1));
        recommendView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.VERTICAL, false));
        recommendView.addItemDecoration(new CommonItemDecoration(container.getContext(), R.drawable.shape_recy_30));
        return view;
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
