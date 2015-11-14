package com.oddsoft.pickashop.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.oddsoft.pickashop.Global.Logger;
import com.oddsoft.pickashop.Models.SuggestionModel;
import com.oddsoft.pickashop.R;

import java.util.ArrayList;

/**
 * Created by afsal on 21/10/15.
 */
public class AutoCompleteSuggestionAdapter extends ArrayAdapter<SuggestionModel> implements Filterable {
    SuggestionModel lastModel;
    private Context mContext;
    private ArrayList<SuggestionModel> mSuggestionList;
    private ArrayList<SuggestionModel> itemsAll;
    private ArrayList<SuggestionModel> suggestions;

    public AutoCompleteSuggestionAdapter(Context context, ArrayList<SuggestionModel> objects) {
        super(context, R.layout.suggestion_cell);
        mContext = context;
        mSuggestionList = objects;
        this.itemsAll = (ArrayList<SuggestionModel>) mSuggestionList.clone();
        this.suggestions = new ArrayList<SuggestionModel>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.suggestion_cell, parent,
                    false);
        }
        TextView headerView = (TextView) rowView.findViewById(R.id.header_name);
        TextView subView = (TextView) rowView.findViewById(R.id.sub_name);
        SuggestionModel model = getItem(position);

        if (position != 0 && model.header.equalsIgnoreCase(lastModel.header)) {
            headerView.setVisibility(View.GONE);
        } else {
            headerView.setVisibility(View.VISIBLE);
        }
        lastModel = model;
        headerView.setText(model.header);
        subView.setText(model.subHeder);

        return rowView;
    }

    @Override
    public Filter getFilter() {
//
        Filter myFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(final CharSequence constraint) {
                ArrayList<SuggestionModel> nLocations = new ArrayList<SuggestionModel>();
                if (constraint != null) {
                    Logger.e(constraint.toString() + "  " + mSuggestionList.size());
                    try {

                        for (SuggestionModel l : mSuggestionList) {
                            if (l.subHeder.toUpperCase().startsWith(constraint.toString().toUpperCase())) {
                                nLocations.add(l);
                            }
                        }
                    } catch (Exception e) {
                    }
                }

                Logger.e(nLocations.size() + " " + constraint);
                final FilterResults filterResults = new FilterResults();
                filterResults.values = nLocations;
                filterResults.count = nLocations.size();

                return filterResults;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(final CharSequence contraint, final FilterResults results) {
                clear();
                for (SuggestionModel address : (ArrayList<SuggestionModel>) results.values) {
                    add(address);
                }
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }

            @Override
            public CharSequence convertResultToString(final Object resultValue) {
                return resultValue == null ? "" : ((SuggestionModel) resultValue).subHeder;
            }
        };
        return myFilter;
    }

}