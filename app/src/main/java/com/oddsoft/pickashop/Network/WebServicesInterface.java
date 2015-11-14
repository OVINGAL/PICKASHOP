package com.oddsoft.pickashop.Network;

import com.oddsoft.pickashop.Models.CompanyDetails;
import com.oddsoft.pickashop.Models.Popular;
import com.oddsoft.pickashop.Models.SearchResult;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public interface WebServicesInterface {

    Response<ArrayList<Popular>> getPopularBrands(String url) throws IOException, JSONException;

    Response<String> getSearchPossibleValues(String url) throws IOException, JSONException;

    Response<ArrayList<SearchResult>> getSearchResult(String url, String params) throws IOException, JSONException;

    Response<CompanyDetails> getCompnyDetails(String url, String params) throws IOException, JSONException;

}
