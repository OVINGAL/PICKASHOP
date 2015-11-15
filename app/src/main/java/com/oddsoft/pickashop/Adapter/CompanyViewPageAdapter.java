package com.oddsoft.pickashop.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.oddsoft.pickashop.Fragments.CompanyContactFragment;
import com.oddsoft.pickashop.Fragments.CompanyFragment;
import com.oddsoft.pickashop.Fragments.ContactFragment;
import com.oddsoft.pickashop.Fragments.FaqFragment;
import com.oddsoft.pickashop.Models.CompanyDetails;

/**
 * Created by afsal on 23/6/15.
 */
public class CompanyViewPageAdapter extends FragmentPagerAdapter {

    Context mContext;
    CompanyDetails mCompanyDetails;

    public CompanyViewPageAdapter(FragmentManager fm, Context context, CompanyDetails companyDetails) {
        super(fm);
        mContext = context;
        mCompanyDetails = companyDetails;
    }


    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("Company", mCompanyDetails);
        switch (position) {
            case 0:
                return CompanyFragment.newInstance(bundle);
            case 1:
                return FaqFragment.newInstance();
            case 2:
                return ContactFragment.newInstance();
            case 3:
                return FaqFragment.newInstance();
            case 4:
                return CompanyContactFragment.newInstance(bundle);
            case 5:
                return ContactFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String heading = "Item";
        switch (position) {
            case 0:
                heading = "HOME";
                break;
            case 1:
                heading = "GALLERY";
                break;
            case 2:
                heading = "PRODUCTS";
                break;
            case 3:
                heading = "OFFERS";
                break;
            case 4:
                heading = "CONTACT US";
                break;
            case 5:
                heading = "ABOUT";
                break;


        }
        return heading;
    }
}
