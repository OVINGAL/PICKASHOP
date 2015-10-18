package com.oddsoft.pickashop;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import com.oddsoft.pickashop.Global.Constants;
import com.oddsoft.pickashop.Global.Utils;
import com.oddsoft.pickashop.Network.Response;
import com.oddsoft.pickashop.Network.Url;
import com.oddsoft.pickashop.Network.WebServicesInterface;
import com.oddsoft.pickashop.Network.webServiceFactory;

import org.json.JSONException;

import java.io.IOException;


public class SplashActivity extends Activity {

    /**
     * Page display time
     */
    private final int SPLASH_DISPLAY_LENGTH = 1500;

    /**
     * Flag to handle the handler
     */
    private boolean mHandlerFlag = true;

    private Handler handler = new Handler();
    private Runnable mrun = new Runnable() {

        @Override
        public void run() {

            if (mHandlerFlag) {
                // Finish the current Activity and start HomePage Activity
                Intent home = new Intent(SplashActivity.this,
                            HomeActivity.class);
                startActivity(home);
                SplashActivity.this.finish();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            // Activity was brought to front and not created,
            // Thus finishing this will get us to the last viewed activity
            finish();
            return;
        }
        /**
         * Run the code inside the runnable after the display time is finished
         * using a handler
         */
        new GetSearchKeys().execute(Url.HOME_SEARCH_SGN);
//        handler.postDelayed(mrun, SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void onBackPressed() {
        /**
         * Disable the handler to execute when user presses back when in
         * SplashPage Activity
         */
        mHandlerFlag = false;
        super.onBackPressed();
    }

    private class GetSearchKeys extends
            AsyncTask<String, Void, Response<String>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Response<String> doInBackground(
                String... params) {

            Response<String> response = new Response<String>();
            WebServicesInterface serviceImpl = webServiceFactory
                    .getWebService(SplashActivity.this);
            String url = params[0];
            try {
                response = serviceImpl.getSearchPossibleValues(url);
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
        protected void onPostExecute(Response<String> response) {
            super.onPostExecute(response);
            if (mHandlerFlag) {
                Utils.setStringSharedPreference(SplashActivity.this, Constants.SHARED_SEARCH_KEYS, response.getResult());
                handler.postDelayed(mrun, SPLASH_DISPLAY_LENGTH);
            }
        }
    }
}
