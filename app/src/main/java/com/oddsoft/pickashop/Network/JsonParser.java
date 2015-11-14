package com.oddsoft.pickashop.Network;

import com.oddsoft.pickashop.HomeActivity;
import com.oddsoft.pickashop.Models.Popular;
import com.oddsoft.pickashop.Models.SearchResult;
import com.oddsoft.pickashop.Models.SuggestionModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;

public class JsonParser {

    public static Response<ArrayList<Popular>> getPopularBrands(String response) {
        Response result = new Response();
        ArrayList<Popular> populars = new ArrayList<>();

        try {
            Object jsonStringer = new JSONTokener(response).nextValue();
            if(jsonStringer instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) jsonStringer;
                for (int i = 0; i < jsonArray.length(); i++) {
                    Popular p = new Popular();
                    JSONObject pop = jsonArray.getJSONObject(i);
                    p.name = pop.getString("company_name");
                    p.companyType = pop.getString("company_type");
                    p.imageUrl = pop.getString("company_image");
                    populars.add(p);
                }
            } else {
                JSONObject json = (JSONObject) jsonStringer;
                JSONArray jsonArray = json.getJSONArray("featured");
                for (int i = 0; i < jsonArray.length(); i++) {
                    Popular p = new Popular();
                    JSONObject pop = jsonArray.getJSONObject(i);
                    p.name = pop.getString("company_name");
                    p.companyType = pop.getString("company_type");
                    p.imageUrl = pop.getString("company_image");
                    populars.add(p);
                }
                JSONArray versionArray = json.getJSONArray("version");
                HomeActivity.PLAYSTORE_VERSION = versionArray.getString(0);
            }
        }catch (JSONException e){
            result.setSuccess(false);
            result.setServerMessage(e.getMessage());
        }
        result.setResult(populars);

        return result;
    }

    public static ArrayList<SuggestionModel> getLocations(String response) {
        ArrayList<SuggestionModel> locationsList = new ArrayList<SuggestionModel>();
        try {
            JSONObject json = new JSONObject(response);
            JSONArray locationArray = json.getJSONArray("location");
            for (int i = 0; i < locationArray.length(); i++) {
                SuggestionModel model = new SuggestionModel();
                JSONObject jsonObject = locationArray.getJSONObject(i);
                model.header = jsonObject.getString("maincity_name");
                model.subHeder = jsonObject.getString("city_name");
                locationsList.add(model);
            }
        } catch (JSONException e) {

        }
        return locationsList;
    }

    public static ArrayList<SuggestionModel> getCategories(String response) {
        ArrayList<SuggestionModel> locationsList = new ArrayList<SuggestionModel>();
        try {
            JSONObject json = new JSONObject(response);
            JSONArray locationArray = json.getJSONArray("category");
            for (int i = 0; i < locationArray.length(); i++) {
                SuggestionModel model = new SuggestionModel();
                JSONObject jsonObject = locationArray.getJSONObject(i);
                model.header = jsonObject.getString("maincat_name");
                model.subHeder = jsonObject.getString("subcat_name");
                locationsList.add(model);
            }
            JSONArray shopArray = json.getJSONArray("shops");
            for (int i = 0; i < shopArray.length(); i++) {
                SuggestionModel model = new SuggestionModel();
                model.header = "Shops";
                model.subHeder = shopArray.getString(i);
                locationsList.add(model);
            }
        } catch (JSONException e) {

        }
        return locationsList;
    }

    public static Response<ArrayList<SearchResult>> getSearchResult(String response) {
        Response<ArrayList<SearchResult>> result = new Response<ArrayList<SearchResult>>();
        ArrayList<SearchResult> locationsList = new ArrayList<SearchResult>();
        try {
            JSONObject json = new JSONObject(response);
            JSONArray locationArray = json.getJSONArray("searchshops");
            for (int i = 0; i < locationArray.length(); i++) {
                SearchResult model = new SearchResult();
                JSONObject jsonObject = locationArray.getJSONObject(i);
                model.company_name = jsonObject.getString("company_name");
                model.company_id = jsonObject.getString("company_id");
                model.company_image = jsonObject.getString("company_image");
                model.company_type = jsonObject.getString("company_type");
                model.company_city = jsonObject.getString("company_city");
                model.company_phone1 = jsonObject.getString("company_phone1");
                model.company_phone2 = jsonObject.getString("company_phone2");
                locationsList.add(model);
            }
            result.setResult(locationsList);
        } catch (JSONException e) {
            result.setSuccess(false);
            result.setServerMessage(e.getMessage());
        }
        return result;
    }

}
