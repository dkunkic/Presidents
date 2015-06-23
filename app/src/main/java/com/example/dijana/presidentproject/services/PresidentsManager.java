package com.example.dijana.presidentproject.services;

import android.content.Context;

import com.example.dijana.presidentproject.models.President;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dijana on 15.6.2015..
 */
public class PresidentsManager {

    private static PresidentsManager instance = null;

    private List<President> presidents;

    public static PresidentsManager getInstance(){
        if (instance == null){
            instance = new PresidentsManager();
        }
        return instance;
    }

    public void loadPresidents(Context context) {
        try {
            FileInputStream fis = context.openFileInput("Presidents");
            ObjectInputStream ois = new ObjectInputStream(fis);
            presidents = (List<President>) ois.readObject();
            ois.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public  void savePresidents(List<President> presidents, Context context){
        try {
            FileOutputStream fos = context.openFileOutput("Presidents", Context.MODE_PRIVATE);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(presidents);
            os.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<President> getPresidents(Context context) {
        if (presidents == null){
            loadPresidents(context);
        }
        return presidents;
    }

    public void setPresidents(List<President> presidents) {
        this.presidents = presidents;
    }

    public void savePresident(President president, Context context){
        List<President> presidents = getPresidents(context);
        for (President p : presidents){
            if (p.getPresidentId() == president.getPresidentId()){
                p.setName(president.getName());
                p.setEmail(president.getEmail());
                p.setYears(president.getYears());
            }
        }
        savePresidents(presidents, context);
    }
}
