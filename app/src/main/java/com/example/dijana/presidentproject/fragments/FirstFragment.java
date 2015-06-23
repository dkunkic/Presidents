package com.example.dijana.presidentproject.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.dijana.presidentproject.R;
import com.example.dijana.presidentproject.adapters.MyRecyclerAdapter;
import com.example.dijana.presidentproject.models.President;
import com.example.dijana.presidentproject.retrofit.RestClient;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class FirstFragment extends Fragment {

    public static FirstFragment newInstance(){

        FirstFragment firstFragment = new FirstFragment();
        return firstFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);
        Button buttonGetPresidents = (Button) view.findViewById(R.id.buttonGetPresidents);

        RecyclerView rcView = (RecyclerView) view.findViewById(R.id.rcView);
        rcView.setLayoutManager(new LinearLayoutManager(getActivity()));

        final MyRecyclerAdapter myRecyclerAdapter =new MyRecyclerAdapter(getActivity(), null);
        rcView.setAdapter(myRecyclerAdapter);

        buttonGetPresidents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RestClient restClient = new RestClient();

                restClient.getApi().getPresidents(new Callback<List<President>>() {
                    @Override
                    public void success(List<President> presidents, Response response) {

                        myRecyclerAdapter.setPresidents(presidents);
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });
            }
        });

        return view;
    }
}
