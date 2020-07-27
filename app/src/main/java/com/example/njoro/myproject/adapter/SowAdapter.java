package com.example.njoro.myproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.model.Sow;
import com.example.njoro.myproject.viewholder.SowViewHolder;

import java.util.List;

public class SowAdapter extends RecyclerView.Adapter<SowViewHolder> {
    private List<Sow> sowList;
    private Context mContext;
    private String TAG = "SowAdapter";
    public SowAdapter(Context context, List<Sow> sowList) {
        this.mContext = context;
        this.sowList =sowList;

    }


    @NonNull
    @Override
    public SowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewsow_layout, parent, false);
        return new SowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SowViewHolder holder, int position) {

        Sow sow =sowList.get(position);
        holder.sowid.setText(sow.getPigId());
        holder.breed.setText(sow.getBreed());
        holder.color.setText(sow.getColor());
        holder.age.setText(sow.getAge());
        holder.weight.setText(sow.getWeight());
        holder.service.setText(sow.getServiceRecord());
        holder.boar.setText(sow.getMatingBoar());
        holder.date.setText(sow.getMatingDate());
        holder.days.setText(sow.getRemainingDays());

        holder.cvsowitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on: " + sowList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return sowList==null?0:sowList.size();
    }
}
