package com.example.dijana.presidentproject.services;

import com.example.dijana.presidentproject.App;
import com.example.dijana.presidentproject.models.President;
import com.example.dijana.presidentproject.retrofit.RestClient;
import com.squareup.otto.Subscribe;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Dijana on 15.6.2015..
 */
public class Services {

    @Subscribe
    public void loadPresidents(Events.PresidentRequest presidentRequest){

        RestClient restClient = new RestClient();

        restClient.getApi().getPresidents(new Callback<List<President>>() {
            @Override
            public void success(List<President> presidents, Response response) {

                Events.PresidentEvent event = new Events.PresidentEvent();
                event.setPresidents(presidents);
                App.getBus().post(event);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }
}
