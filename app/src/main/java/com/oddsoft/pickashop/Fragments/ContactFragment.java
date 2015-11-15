package com.oddsoft.pickashop.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oddsoft.pickashop.R;


public class ContactFragment extends Fragment {

    public static ContactFragment newInstance() {
        return new ContactFragment();
    }

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
        View rootView = inflater.inflate(R.layout.fragment_contact, container, false);
        rootView.findViewById(R.id.contact_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = "tel:+914846560560";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(m));
                startActivity(callIntent);
            }
        });
        rootView.findViewById(R.id.contact_mob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = "tel:+91 9447777621";
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse(m));
                startActivity(callIntent);
            }
        });
        rootView.findViewById(R.id.fb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = "https://www.facebook.com/PickaShop4U";
                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                callIntent.setData(Uri.parse(m));
                startActivity(callIntent);
            }
        });
        rootView.findViewById(R.id.twitter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = "https://twitter.com/pickashop4u";
                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                callIntent.setData(Uri.parse(m));
                startActivity(callIntent);
            }
        });
        rootView.findViewById(R.id.gp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String m = "https://plus.google.com/u/0/105782887746110634429";
                Intent callIntent = new Intent(Intent.ACTION_VIEW);
                callIntent.setData(Uri.parse(m));
                startActivity(callIntent);
            }
        });
        return rootView;
    }



}
