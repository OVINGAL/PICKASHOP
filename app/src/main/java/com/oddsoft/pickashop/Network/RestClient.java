package com.oddsoft.pickashop.Network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {

    static int connectiontimeout = 15000;
    static int sockettimeout = 10000;

    public static String httpGet(String myurl) throws IOException {
        URL url = new URL(myurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(10000 /* milliseconds */);
//            conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        int response = conn.getResponseCode();
        InputStream is = conn.getInputStream();
        String contentAsString = readIt(is);
//            Logger.e("The response is: " + response + "  " + contentAsString);
        return contentAsString;

    }

    public static String httpPost(String myurl, String params) throws IOException {
        URL url = new URL(myurl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(10000 /* milliseconds */);
//            conn.setConnectTimeout(15000 /* milliseconds */);
        conn.setRequestMethod("POST");
        conn.setUseCaches(false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(params);
        wr.flush();
        wr.close();
        conn.connect();
        int response = conn.getResponseCode();
        InputStream is = conn.getInputStream();
        String contentAsString = readIt(is);
//            Logger.e("The response is: " + response + "  " + contentAsString);
        return contentAsString;

    }

    // Reads an InputStream and converts it to a String.
    public static String readIt(InputStream stream) throws IOException {

        BufferedReader r = new BufferedReader(new InputStreamReader(stream));
        StringBuilder total = new StringBuilder();
        String line;
        while ((line = r.readLine()) != null) {
            total.append(line);
        }
        return total.toString();
    }

}
