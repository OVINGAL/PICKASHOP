package com.oddsoft.pickashop.Network;

import android.content.Context;

import com.oddsoft.pickashop.Global.Logger;
import com.oddsoft.pickashop.Models.CompanyDetails;
import com.oddsoft.pickashop.Models.Popular;
import com.oddsoft.pickashop.Models.SearchResult;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class WebServiceImp implements WebServicesInterface {

    private Context context;

    public WebServiceImp() {
    }

    public WebServiceImp(Context context) {
        this.context = context;
    }

    @Override
    public Response<ArrayList<Popular>> getPopularBrands(String url) throws IOException, JSONException {
        String serverResponse = RestClient.httpGet(url);
        Logger.i("serverresponse : " + serverResponse);
        return JsonParser.getPopularBrands(serverResponse);
    }

    @Override
    public Response<String> getSearchPossibleValues(String url) throws IOException, JSONException {
        String serverResponse = RestClient.httpGet(url);
        Logger.i("serverresponse : " + serverResponse);
        Response<String> response = new Response<String>();
        response.setResult(serverResponse);
        return response;
    }

    @Override
    public Response<ArrayList<SearchResult>> getSearchResult(String url, String params) throws IOException, JSONException {
        String serverResponse = RestClient.httpPost(url, params);
        Logger.i("serverresponse : " + serverResponse);
        return JsonParser.getSearchResult(serverResponse);
    }

    @Override
    public Response<CompanyDetails> getCompnyDetails(String url, String params) throws IOException, JSONException {
        String serverResponse = RestClient.httpPost(url, params);
        Logger.i("serverresponse : " + serverResponse);
        return JsonParser.getDetails(serverResponse);
    }
}
