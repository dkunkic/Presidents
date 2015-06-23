package com.example.dijana.presidentproject;

import android.app.Application;

import com.example.dijana.presidentproject.services.PresidentsManager;
import com.example.dijana.presidentproject.services.Services;
import com.squareup.otto.Bus;

/**
 * Created by Dijana on 15.6.2015..
 */
public class App extends Application{

    private static Bus bus;

    public static Bus getBus(){

        if(bus == null){
            bus = new Bus();
        }
        return bus;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        getBus().register(new Services());

        PresidentsManager.getInstance().loadPresidents(getApplicationContext());
    }
}
