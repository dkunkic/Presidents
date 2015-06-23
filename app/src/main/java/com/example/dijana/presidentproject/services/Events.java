package com.example.dijana.presidentproject.services;

import com.example.dijana.presidentproject.models.President;

import java.util.List;

/**
 * Created by Dijana on 15.6.2015..
 */
public class Events {

    public static class PresidentRequest{

    }

    public static class PresidentEvent{

        private List<President> presidents;

        public List<President> getPresidents() {
            return presidents;
        }

        public void setPresidents(List<President> presidents) {
            this.presidents = presidents;
        }
    }
}
