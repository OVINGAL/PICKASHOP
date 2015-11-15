package com.oddsoft.pickashop.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oddsoft.pickashop.Models.CompanyDetails;
import com.oddsoft.pickashop.R;


public class GalleryFragment extends Fragment {

    CompanyDetails mCompanyDetails;

    public static GalleryFragment newInstance(Bundle bundle) {
        GalleryFragment companyFragment = new GalleryFragment();
        companyFragment.setArguments(bundle);
        return companyFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCompanyDetails = (CompanyDetails) getArguments().getSerializable("Company");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout_white_bg for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);


        return rootView;
    }


}
