package com.oddsoft.pickashop.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oddsoft.pickashop.Adapter.CompanyViewPageAdapter;
import com.oddsoft.pickashop.R;
import com.oddsoft.pickashop.Widgets.SlidingTabLayout;


public class CompanyHomeFragment extends Fragment {

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout_white_bg for this fragment
        View rootView = inflater.inflate(R.layout.company_home_fragment, container, false);

        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        mViewPager.setAdapter(new CompanyViewPageAdapter(getFragmentManager(), getActivity()));

        mSlidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setDistributeEvenly(true);
        mSlidingTabLayout.setViewPager(mViewPager);

        return rootView;
    }


}
