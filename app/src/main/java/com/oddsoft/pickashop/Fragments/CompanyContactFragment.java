package com.oddsoft.pickashop.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oddsoft.pickashop.Models.CompanyDetails;
import com.oddsoft.pickashop.R;


public class CompanyContactFragment extends Fragment {

    CompanyDetails mCompanyDetails;

    public static CompanyContactFragment newInstance(Bundle bundle) {
        CompanyContactFragment companyFragment = new CompanyContactFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        ((TextView) rootView.findViewById(R.id.comp_name_fn)).setVisibility(View.GONE);
        ((TextView) rootView.findViewById(R.id.comp_about)).setVisibility(View.GONE);
        ((TextView) rootView.findViewById(R.id.comp_phone_fn)).setText(mCompanyDetails.phone1 + " , " + mCompanyDetails.phone2);
        ((TextView) rootView.findViewById(R.id.comp_fax)).setText(mCompanyDetails.fax1 + " , " + mCompanyDetails.fax2);
        ((TextView) rootView.findViewById(R.id.comp_toll)).setText(mCompanyDetails.toll1 + " , " + mCompanyDetails.toll2);
        ((TextView) rootView.findViewById(R.id.comp_web)).setText(mCompanyDetails.website);
        ((TextView) rootView.findViewById(R.id.comp_email)).setText(mCompanyDetails.mail);

        ((TextView) rootView.findViewById(R.id.comp_building)).setText(mCompanyDetails.building);
        ((TextView) rootView.findViewById(R.id.comp_streat)).setText(mCompanyDetails.streat);
        ((TextView) rootView.findViewById(R.id.comp_land)).setText(mCompanyDetails.landmark);
        ((TextView) rootView.findViewById(R.id.comp_city_fn)).setText(mCompanyDetails.city);
        ((TextView) rootView.findViewById(R.id.comp_pin)).setText(mCompanyDetails.district + " - " + mCompanyDetails.pincode);

        return rootView;
    }


}
