package com.example.dijana.presidentproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dijana.presidentproject.models.President;
import com.example.dijana.presidentproject.R;
import com.example.dijana.presidentproject.services.PresidentsManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dijana on 15.6.2015..
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> implements Filterable{

    private final Context context;
    private List<President> originPresidents;
    private List<President> presidents;
    private ClickListener clickListener;

    public MyRecyclerAdapter(Context context, ClickListener clickListener) {
        this.context = context;
        this.clickListener = clickListener;
    }

    @Override
    public MyRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_holder, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view, clickListener);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerAdapter.ViewHolder viewHolder, int i) {

        if(presidents == null || presidents.isEmpty()) {
            return;
        }

        Picasso.with(context).load(presidents.get(i).getPhoto_url()).into(viewHolder.presidentPhoto);
        viewHolder.presidentName.setText("Name: " + presidents.get(i).getName());
        viewHolder.presidentYears.setText("Years: " + presidents.get(i).getYears());


    }

    public void setPresidents(List<President> presidents){

        this.presidents = presidents;
        notifyDataSetChanged();
    }

    public List<President> getPresidents(){
        return presidents;
    }

    @Override
    public int getItemCount() {

        if(presidents == null){
            return 0;
        } else {
            return presidents.size();
        }

    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults oReturn = new FilterResults();
                List<President> tempPresidents = new ArrayList<>();
                if (constraint != null) {
                    for (President president : originPresidents) {
                        if (president.getName().toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                            tempPresidents.add(president);
                        }
                    }
                }
                oReturn.values = tempPresidents;
                return oReturn;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                presidents = (List<President>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public ImageView presidentPhoto;
        public TextView presidentName;
        public TextView presidentYears;
        public Button detailsButton;

        private ClickListener clickListener;

        public ViewHolder(View itemView, ClickListener clickListener) {
            super(itemView);
            this.clickListener = clickListener;

            presidentPhoto = (ImageView) itemView.findViewById(R.id.presidentPhoto);
            presidentName = (TextView) itemView.findViewById(R.id.presidentName);
            presidentYears = (TextView) itemView.findViewById(R.id.presidentYears);
            detailsButton = (Button) itemView.findViewById(R.id.detailsButton);

            if (this.clickListener != null) {
                detailsButton.setOnClickListener(this);
                detailsButton.setOnLongClickListener(this);
            }

        }

        public void setClickListener(ClickListener clickListener) {
            this.clickListener = clickListener;
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition(), false);

        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick(v, getPosition(), true);
            return true;
        }
    }
    public interface ClickListener{
        public void onClick(View v, int position, boolean isLongClick);
    }
}
