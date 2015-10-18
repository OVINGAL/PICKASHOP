package com.oddsoft.pickashop.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.oddsoft.pickashop.Fragments.CompanyHomeFragment;
import com.oddsoft.pickashop.Fragments.HomeFragment;
import com.oddsoft.pickashop.Global.Constants;
import com.oddsoft.pickashop.HomeActivity;
import com.oddsoft.pickashop.Models.Popular;
import com.oddsoft.pickashop.R;

import java.util.ArrayList;

/**
 * Created by afsal on 8/6/15.
 */
public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    Context context;
    private ArrayList<Popular> populars;
    private int lastPosition = -1;

    // Provide a suitable constructor (depends on the kind of dataset)
    public PopularAdapter(ArrayList<Popular> populars, Context mContext) {
        this.populars = populars;
        this.context = mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PopularAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.popular_cell, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v, new ViewHolder.IMyViewHolderClicks() {
            @Override
            public void onItemClick(View view, int pos) {
                startDetails(context, populars.get(pos));
            }
        });
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mName.setText(populars.get(position).name);
        try {
            Glide.with(context).load(populars.get(position).imageUrl)
                    .error(R.drawable.logo)
                    .into(holder.mProfileImg);
        } catch (IllegalArgumentException e) {

        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return populars.size();
    }


    public void startDetails(Context context, Popular model) {
        HomeFragment.stopMove = false;
        CompanyHomeFragment mCompanyHomeFragment;
        FragmentManager frMng = ((HomeActivity) context).getSupportFragmentManager();

        Fragment fr = frMng.findFragmentByTag(Constants.COMPANY_HOME_FRAGMENT_TAG);
        if (fr != null) {
            mCompanyHomeFragment = (CompanyHomeFragment) fr;
        } else {
            mCompanyHomeFragment = new CompanyHomeFragment();
        }
        ((HomeActivity) context).setFragmentOthers(mCompanyHomeFragment, Constants.COMPANY_HOME_FRAGMENT_TAG);

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mName;
        public ImageView mProfileImg;

        public IMyViewHolderClicks mListener;

        public ViewHolder(View v, IMyViewHolderClicks listener) {
            super(v);
            mListener = listener;
            mName = (TextView) v.findViewById(R.id.brand_name);
            mProfileImg = (ImageView) v.findViewById(R.id.brand_img);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int itemPosition = getAdapterPosition();
            mListener.onItemClick(view, itemPosition);
//
        }

        public interface IMyViewHolderClicks {
            void onItemClick(View view, int pos);
        }
    }

}
