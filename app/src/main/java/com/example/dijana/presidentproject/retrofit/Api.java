package com.example.dijana.presidentproject.retrofit;

import com.example.dijana.presidentproject.models.President;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Dijana on 15.6.2015..
 */
public interface Api {

    @GET("/presidents")
    public void getPresidents(Callback<List<President>> callback);

}
