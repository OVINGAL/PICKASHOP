package com.oddsoft.pickashop.Network;

import android.content.Context;

public class webServiceFactory {

    public static final WebServicesInterface getWebService() {
        return new WebServiceImp();
    }

    public static final WebServicesInterface getWebService(Context context) {
        return new WebServiceImp(context);
    }


}
