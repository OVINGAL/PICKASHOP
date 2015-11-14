package com.oddsoft.pickashop.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.oddsoft.pickashop.Adapter.AutoCompleteSuggestionAdapter;
import com.oddsoft.pickashop.Adapter.PopularAdapter;
import com.oddsoft.pickashop.Global.Constants;
import com.oddsoft.pickashop.Global.Utils;
import com.oddsoft.pickashop.Models.Popular;
import com.oddsoft.pickashop.Models.SuggestionModel;
import com.oddsoft.pickashop.Network.JsonParser;
import com.oddsoft.pickashop.Network.Response;
import com.oddsoft.pickashop.Network.Url;
import com.oddsoft.pickashop.Network.WebServicesInterface;
import com.oddsoft.pickashop.Network.webServiceFactory;
import com.oddsoft.pickashop.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class HomeFragment extends Fragment {

    public static boolean stopMove = true;
    GetPopularBrands getPopularBrands;
    ArrayList<Popular> populars;
    int pos = 0, increament = 1;
    Handler handler;
    Runnable runnable;
    PopularAdapter adapter;
    ProgressBar progressBar;
    AutoCompleteTextView location, product;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

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
        View rootView = inflater.inflate(R.layout.fragment_home2, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.home_list);
        mLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        populars = new ArrayList<Popular>();
//        populars.add(new Popular("Multiplex", "http://pickashop.com/mediafiles/comp-pht-4/basic/logo.jpg"));
//        populars.add(new Popular("World of Foot Wears", "http://pickashop.com/mediafiles/comp-c2e-2/basic/logo.jpg"));
//        populars.add(new Popular("Joyson Group", "http://pickashop.com/mediafiles/comp-d2z-1/basic/logo.jpg"));
//        populars.add(new Popular("Dress World", "http://pickashop.com/images/default/logo1.png"));
//        populars.add(new Popular("OddSoft", "http://pickashop.com/images/logo.png"));
//        populars.add(new Popular("Cabot", "http://pickashop.com/images/e2.jpg"));

        adapter = new PopularAdapter(populars, getActivity());
        mRecyclerView.setAdapter(adapter);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        getPopularBrands = new GetPopularBrands();
        getPopularBrands.execute(Url.HOME_POPULAR_URL);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                if (stopMove) {
                if (pos == populars.size() - 1) {
                    increament = -1;
                } else if (pos == 0) {
                    increament = 1;
                }
                pos = pos + increament;
                    mRecyclerView.smoothScrollToPosition(pos);
                    handler.postDelayed(runnable, 1500);
                }
            }
        };
        handler.postDelayed(runnable, 1500);

        location = (AutoCompleteTextView) rootView.findViewById(R.id.search_edit);
        product = (AutoCompleteTextView) rootView.findViewById(R.id.search_for);

        ArrayList<SuggestionModel> locations, categories;
        locations = JsonParser.getLocations(Utils.getStringSharedPreference(getActivity(), Constants.SHARED_SEARCH_KEYS));
        categories = JsonParser.getCategories(Utils.getStringSharedPreference(getActivity(), Constants.SHARED_SEARCH_KEYS));
        AutoCompleteSuggestionAdapter adapter = new AutoCompleteSuggestionAdapter(getActivity(), locations);
        AutoCompleteSuggestionAdapter adapterCategory = new AutoCompleteSuggestionAdapter(getActivity(), categories);
        location.setThreshold(1);
        product.setThreshold(1);

        location.setAdapter(adapter);
        product.setAdapter(adapterCategory);
//


//        String[] language ={"C","C++","Java",".NET","iPhone","Android","ASP.NET","PHP"};
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(getActivity(),android.R.layout.select_dialog_item,language);
//        location.setAdapter(adapter2);
//        product.setAdapter(adapter2);
        return rootView;
    }

    @Override
    public void onResume() {
        stopMove = true;
        super.onResume();
    }

    private class GetPopularBrands extends
            AsyncTask<String, Void, Response<ArrayList<Popular>>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Response<ArrayList<Popular>> doInBackground(
                String... params) {

            Response<ArrayList<Popular>> response = new Response<ArrayList<Popular>>();
            WebServicesInterface serviceImpl = webServiceFactory
                    .getWebService(getActivity());
            String url = params[0];
            try {
                response = serviceImpl.getPopularBrands(url);
            } catch (JSONException e) {
                response.setThrowable(e);
                e.printStackTrace();
            } catch (IOException e) {
                response.setThrowable(e);
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(Response<ArrayList<Popular>> response) {
            super.onPostExecute(response);
            progressBar.setVisibility(View.GONE);

            if (response.isSuccess()) {
                populars.removeAll(populars);
                populars.clear();
                populars.addAll(response.getResult());
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(getActivity(),response.getServerMessage(),Toast.LENGTH_SHORT).show();
            }

        }
    }
}
