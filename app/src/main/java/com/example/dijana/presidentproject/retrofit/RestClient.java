package com.example.dijana.presidentproject.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Created by Dijana on 15.6.2015..
 */
public class RestClient {

    private Api api;

    public RestClient(){

        Gson gson = new GsonBuilder().create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://178.79.178.91:7878")
                .setConverter(new GsonConverter(gson))
                .build();

        api = restAdapter.create(Api.class);
    }

    public Api getApi(){
        return api;
    }
}
