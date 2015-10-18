package com.oddsoft.pickashop.Network;

import com.oddsoft.pickashop.Models.Popular;

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
            }
        }catch (JSONException e){
            result.setSuccess(false);
            result.setServerMessage(e.getMessage());
        }
        result.setResult(populars);

        return result;
    }

}
