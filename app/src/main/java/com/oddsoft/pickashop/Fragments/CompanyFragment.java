package com.oddsoft.pickashop.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oddsoft.pickashop.Models.CompanyDetails;
import com.oddsoft.pickashop.R;


public class CompanyFragment extends Fragment {

    CompanyDetails mCompanyDetails;

    public static CompanyFragment newInstance(Bundle bundle) {
        CompanyFragment companyFragment = new CompanyFragment();
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
        ((TextView) rootView.findViewById(R.id.comp_name_fn)).setText(mCompanyDetails.company_name + " - " + mCompanyDetails.company_type);
        ((TextView) rootView.findViewById(R.id.comp_about)).setText(Html.fromHtml(mCompanyDetails.about));
        final SpannableString s = new SpannableString(mCompanyDetails.phone1 + " , " + mCompanyDetails.phone2);
        Linkify.addLinks(s, Linkify.PHONE_NUMBERS);
        ((TextView) rootView.findViewById(R.id.comp_phone_fn)).setText(s);
        ((TextView) rootView.findViewById(R.id.comp_phone_fn)).setMovementMethod(LinkMovementMethod.getInstance());

        ((TextView) rootView.findViewById(R.id.comp_fax)).setText(mCompanyDetails.fax1 + " , " + mCompanyDetails.fax2);
        ((TextView) rootView.findViewById(R.id.comp_toll)).setText(mCompanyDetails.toll1 + " , " + mCompanyDetails.toll2);

        final SpannableString s1 = new SpannableString(mCompanyDetails.website);
        Linkify.addLinks(s1, Linkify.WEB_URLS);

        ((TextView) rootView.findViewById(R.id.comp_web)).setText(s1);
        ((TextView) rootView.findViewById(R.id.comp_web)).setMovementMethod(LinkMovementMethod.getInstance());

        final SpannableString s2 = new SpannableString(mCompanyDetails.mail);
        Linkify.addLinks(s2, Linkify.EMAIL_ADDRESSES);

        ((TextView) rootView.findViewById(R.id.comp_email)).setText(s2);
        ((TextView) rootView.findViewById(R.id.comp_email)).setMovementMethod(LinkMovementMethod.getInstance());

        ((TextView) rootView.findViewById(R.id.comp_building)).setText(mCompanyDetails.building);
        ((TextView) rootView.findViewById(R.id.comp_streat)).setText(mCompanyDetails.streat);
        ((TextView) rootView.findViewById(R.id.comp_land)).setText(mCompanyDetails.landmark);
        ((TextView) rootView.findViewById(R.id.comp_city_fn)).setText(mCompanyDetails.city);
        ((TextView) rootView.findViewById(R.id.comp_pin)).setText(mCompanyDetails.district + " - " + mCompanyDetails.pincode);

        return rootView;
    }


}
