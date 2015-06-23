package com.example.dijana.presidentproject.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.dijana.presidentproject.App;
import com.example.dijana.presidentproject.R;
import com.example.dijana.presidentproject.adapters.MyRecyclerAdapter;
import com.example.dijana.presidentproject.services.Events;
import com.example.dijana.presidentproject.services.PresidentsManager;
import com.squareup.otto.Subscribe;


public class SecondFragment extends Fragment {
    private MyRecyclerAdapter myRecyclerAdapter;

    public static SecondFragment newInstance(){

        SecondFragment secondFragment = new SecondFragment();
        return secondFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myRecyclerAdapter = new MyRecyclerAdapter(getActivity(), null);

        recyclerView.setAdapter(myRecyclerAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        App.getBus().register(this);
        App.getBus().post(new Events.PresidentRequest());
    }

    @Override
    public void onPause() {
        super.onPause();

        App.getBus().unregister(this);
    }

    @Subscribe
    public void PresidentSuccess(Events.PresidentEvent event){
        myRecyclerAdapter.setPresidents(event.getPresidents());
        if (PresidentsManager.getInstance().getPresidents(getActivity()) == null)
            PresidentsManager.getInstance().savePresidents(event.getPresidents(), getActivity());
    }
}
