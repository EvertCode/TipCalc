package com.evertcode.calctip;

import android.app.Application;

/**
 * Created by Hebert on 04/06/2016.
 */
public class CalcTipAplication extends Application {
    private final static String ABOUT_URL ="https://twitter.com/EvertCode";

    public String getAboutUrl() {
        return ABOUT_URL;
    }
}
