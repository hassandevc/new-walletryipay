package com.appsflyer.androidsampleapp;

//import com.appsflyer.AppsFlyerLib;

import com.appsflyer.AppsFlyerLib;

import android.app.Application;
import java.util.Map;

class AppsflyerBasicApp extends Application {
    private static final String afDevKey = "gLxKSCh96zLqzYywUyQDaY";

    public static final String LOG_TAG = "AppsFlyerOneLinkSimApp";
    public static final String DL_ATTRS = "dl_attrs";
    Map<String, Object> conversionData = null;
    private Object AppsFlyerConstants;

    @Override
    public void onCreate() {
        super.onCreate();
/*
        String afDevKey = AppsFlyerConstants.afDevKey;
*/
        AppsFlyerLib appsflyer = AppsFlyerLib.getInstance();
        // For debug - remove in production
        appsflyer.setDebugLog(true);
        //optional
        appsflyer.setMinTimeBetweenSessions(0);

        appsflyer.init(afDevKey, null, this);
        appsflyer.start(this);
    }
}