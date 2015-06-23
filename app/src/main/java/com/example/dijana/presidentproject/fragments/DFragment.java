package com.example.dijana.presidentproject.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dijana.presidentproject.R;
import com.example.dijana.presidentproject.adapters.MyRecyclerAdapter;
import com.example.dijana.presidentproject.models.President;
import com.example.dijana.presidentproject.services.PresidentsManager;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dijana on 17.6.2015..
 */
public class DFragment extends DialogFragment {

    private President president;
    private ImageView presidentPhoto;
    private EditText presidentName;
    private EditText presidentEmail;
    private EditText presidentYears;
    private Button saveButton;
    private ThirdFragment.DialogListener listener;


    public static DFragment newInstance(President president) {
        DFragment dFragment = new DFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("PRESIDENT", (Serializable) president);
        dFragment.setArguments(bundle);
        return dFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.details, container, false);

        presidentPhoto = (ImageView) rootView.findViewById(R.id.presidentPhoto);
        presidentName = (EditText) rootView.findViewById(R.id.presidentName);
        presidentEmail = (EditText) rootView.findViewById(R.id.presidentEmail);
        presidentYears = (EditText) rootView.findViewById(R.id.presidentYears);
        saveButton = (Button) rootView.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(saveClickListener);

        getDialog().setTitle("Details of President");
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        president = (President) getArguments().getSerializable("PRESIDENT");

        Picasso.with(getActivity()).load(president.getPhoto_url()).into(presidentPhoto);
        presidentName.setText(president.getName());
        presidentEmail.setText(president.getEmail());
        presidentYears.setText(president.getYears());

    }

    private View.OnClickListener saveClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!isEmailValid(presidentEmail.getText().toString())) {
                presidentEmail.setError("Wrong email");
            } else {
                president.setEmail(presidentEmail.getText().toString());
                president.setYears(presidentYears.getText().toString());
                president.setName(presidentName.getText().toString());

                PresidentsManager.getInstance().savePresident(president, getActivity());
                listener.refreshDialog();
                dismiss();
            }
        }
    };

    public boolean isEmailValid(String email)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    public void setListener(ThirdFragment.DialogListener listener) {
        this.listener = listener;
    }
}
