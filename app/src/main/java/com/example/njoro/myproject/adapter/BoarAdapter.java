package com.example.njoro.myproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.model.Boar;
import com.example.njoro.myproject.viewholder.BoarViewHolder;

import java.util.List;

public class BoarAdapter extends RecyclerView.Adapter<BoarViewHolder> {
    private List<Boar> boarList;
    private Context mContext;
    private String TAG = "BoarAdapter";
    public BoarAdapter(Context context, List<Boar> boarList) {
        this.mContext = context;
        this.boarList = boarList;

    }

    @NonNull
    @Override
    public BoarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewboars_layout, parent, false);
        return new BoarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoarViewHolder holder, int position) {
        Boar boar =boarList.get(position);
        holder.boarid.setText(boar.getPigId());
        holder.breed.setText(boar.getBreed());
        holder.color.setText(boar.getColor());
        holder.age.setText(boar.getAge());
        holder.weight.setText(boar.getWeight());
        holder.service.setText(boar.getServiceRecord());
        holder.sow.setText(boar.getMatingSow());
        holder.date.setText(boar.getMatingDate());
        holder.days.setText(boar.getRemainingDays());

        holder.cvboarsitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on: " + boarList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return boarList==null?0:boarList.size();
    }
}
