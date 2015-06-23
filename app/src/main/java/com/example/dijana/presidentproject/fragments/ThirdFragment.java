package com.example.dijana.presidentproject.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.dijana.presidentproject.R;
import com.example.dijana.presidentproject.adapters.MyRecyclerAdapter;
import com.example.dijana.presidentproject.models.President;
import com.example.dijana.presidentproject.services.PresidentsManager;


public class ThirdFragment extends Fragment{

    private MyRecyclerAdapter myRecyclerAdapter;
    private EditText searchEditText;

    public static ThirdFragment newInstance() {
        ThirdFragment thirdFragment = new ThirdFragment();
        return thirdFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_third, container,false);
        searchEditText = (EditText) view.findViewById(R.id.searchEditText);

        RecyclerView rcView = (RecyclerView) view.findViewById(R.id.rcView);
        rcView.setLayoutManager(new LinearLayoutManager(getActivity()));

        myRecyclerAdapter = new MyRecyclerAdapter(getActivity(), clickListener);
        rcView.setAdapter(myRecyclerAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        myRecyclerAdapter.setPresidents(PresidentsManager.getInstance().getPresidents(getActivity()));

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                myRecyclerAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    MyRecyclerAdapter.ClickListener clickListener = new MyRecyclerAdapter.ClickListener() {
        @Override
        public void onClick(View v, int position, boolean isLongClick) {
            openDialog(myRecyclerAdapter.getPresidents().get(position));
        }
    };

    private void openDialog(President president) {
        DFragment dFragment = DFragment.newInstance(president);
        dFragment.setListener(dialogListener);
        dFragment.show(getFragmentManager(), "Dialog Fragment");
    }

    private DialogListener dialogListener = new DialogListener() {
        @Override
        public void refreshDialog() {
            myRecyclerAdapter.setPresidents(PresidentsManager.getInstance().getPresidents(getActivity()));
        }
    };

    public interface DialogListener {
        void refreshDialog();
    }
}
