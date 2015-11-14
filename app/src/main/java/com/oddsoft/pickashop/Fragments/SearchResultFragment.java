package com.oddsoft.pickashop.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oddsoft.pickashop.Adapter.SearchResultAdapter;
import com.oddsoft.pickashop.Global.Logger;
import com.oddsoft.pickashop.Models.SearchResult;
import com.oddsoft.pickashop.Network.Response;
import com.oddsoft.pickashop.Network.Url;
import com.oddsoft.pickashop.Network.WebServicesInterface;
import com.oddsoft.pickashop.Network.webServiceFactory;
import com.oddsoft.pickashop.R;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class SearchResultFragment extends Fragment {

    int mPastVisiblesItems, mVisibleItemCount, mTotalItemCount;
    Boolean isPullToRefresh = false;
    ArrayList<SearchResult> searchResults;
    String location, shop;
    GetSearchResult getSearchResult;
    int page = 1;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private LinearLayoutManager mLayoutManager;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mLoading = true;

    public static SearchResultFragment newInstance() {

        return new SearchResultFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            searchResults = bundle.getParcelableArrayList("RESULT");
            location = bundle.getString("Location");
            shop = bundle.getString("Shop");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout_white_bg for this fragment
        View rootView = inflater.inflate(R.layout.fragment_search_result, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.list);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                mVisibleItemCount = mLayoutManager.getChildCount();
                mTotalItemCount = mLayoutManager.getItemCount();
                mPastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                if (mLoading) {
                    if ((mVisibleItemCount + mPastVisiblesItems) >= mTotalItemCount) {
                        mLoading = false;
                        Logger.e("Load More");
                        page++;
                        String url = Url.SEARCH_URL;
                        String params = "picktag=search_result&" + "location=" + location
                                + "&keywords=" + shop + "&type=shops&page=" + page + "&perPage=10";
                        getSearchResult = new GetSearchResult();
                        getSearchResult.execute(url, params);
                    }
                }
            }
        });


        mAdapter = new SearchResultAdapter(searchResults, getActivity());
        mRecyclerView.setAdapter(mAdapter);
//        setHasOptionsMenu(true);

        return rootView;
    }

    private class GetSearchResult extends
            AsyncTask<String, Void, Response<ArrayList<SearchResult>>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Response<ArrayList<SearchResult>> doInBackground(
                String... params) {

            Response<ArrayList<SearchResult>> response = new Response<ArrayList<SearchResult>>();
            WebServicesInterface serviceImpl = webServiceFactory
                    .getWebService(getActivity());
            String url = params[0];
            try {
                response = serviceImpl.getSearchResult(url, params[1]);
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
        protected void onPostExecute(Response<ArrayList<SearchResult>> response) {
            super.onPostExecute(response);

            if (response.isSuccess()) {
                ArrayList<SearchResult> results = response.getResult();
                if (results.size() > 0) {
                    mLoading = true;
                    searchResults.addAll(results);
                    mAdapter.notifyDataSetChanged();
                }
            } else {
            }

        }
    }

}
