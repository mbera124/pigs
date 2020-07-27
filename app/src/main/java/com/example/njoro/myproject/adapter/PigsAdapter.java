package com.example.njoro.myproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.njoro.myproject.R;
import com.example.njoro.myproject.model.Pigs;
import com.example.njoro.myproject.viewholder.BoarViewHolder;
import com.example.njoro.myproject.viewholder.PigsViewHolder;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PigsAdapter extends RecyclerView.Adapter<PigsViewHolder> {
//StorageReference storageReference;
    private List<Pigs> pigsList;
    private Context mContext;
    private String TAG = "PigsAdapter";
    public PigsAdapter(Context context, List<Pigs> pigsList) {
        this.mContext = context;
        this.pigsList = pigsList;

    }
    @NonNull
    @Override
    public PigsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewpigs_layout, parent, false);
        return new PigsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PigsViewHolder holder, int position) {
        Pigs pigs =pigsList.get(position);
        holder.contact.setText(pigs.getContact());
        holder.price.setText(pigs.getPrice());
//     StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("images");
       Picasso.get().load(pigs.getImageURL()).placeholder(R.drawable.piggy).into(holder.imgpig);
//Glide.with(mContext).load(pigs.getImageURL()).into(holder.imgpig);
        holder.cvpigsitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on: " + pigsList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return pigsList==null?0:pigsList.size();
    }
}
