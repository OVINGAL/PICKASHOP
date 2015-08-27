package com.oddsoft.pickashop.Network;

import com.oddsoft.pickashop.Models.Popular;

import java.util.ArrayList;

public class JsonParser {

    public static Response<ArrayList<Popular>> getPopularBrands(String response) {
        Response result = new Response();
        ArrayList<Popular> populars = new ArrayList<>();
        result.setResult(populars);

        return result;
    }

}
