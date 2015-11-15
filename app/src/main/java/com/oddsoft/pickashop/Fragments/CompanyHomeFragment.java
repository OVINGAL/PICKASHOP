package com.oddsoft.pickashop.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oddsoft.pickashop.Adapter.CompanyViewPageAdapter;
import com.oddsoft.pickashop.Models.CompanyDetails;
import com.oddsoft.pickashop.Models.SearchResult;
import com.oddsoft.pickashop.R;
import com.oddsoft.pickashop.Widgets.SlidingTabLayout;


public class CompanyHomeFragment extends Fragment {

    SearchResult mSearch;
    CompanyDetails mCompany;
    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            mSearch = bundle.getParcelable("Selected");
            mCompany = (CompanyDetails) bundle.getSerializable("Company");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout_white_bg for this fragment
        View rootView = inflater.inflate(R.layout.company_home_fragment, container, false);

        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(new CompanyViewPageAdapter(getChildFragmentManager(), getActivity(), mCompany));

        mSlidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setSelectedIndicatorColors(getResources().getColor(R.color.colorAccent));
        mSlidingTabLayout.setDistributeEvenly(false);
        mSlidingTabLayout.setViewPager(mViewPager);
        ImageView logo = (ImageView) rootView.findViewById(R.id.img_avathar_comp);
        try {
            Glide.with(getActivity()).load(mCompany.banner)
                    .error(R.drawable.banner)
                    .into(logo);
        } catch (IllegalArgumentException e) {

        }
        ((TextView) rootView.findViewById(R.id.comp_name_dt)).setText(mSearch.company_name);
        ((TextView) rootView.findViewById(R.id.comp_city_dt)).setText(mSearch.company_city);

        return rootView;
    }


}
