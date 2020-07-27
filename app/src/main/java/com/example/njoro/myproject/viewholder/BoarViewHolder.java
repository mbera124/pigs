package com.example.njoro.myproject.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njoro.myproject.ItemClickListener;
import com.example.njoro.myproject.R;

public class BoarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
   public TextView boarid,breed,color,age,weight,service,sow,date,days;
   public CardView cvboarsitem;
    private ItemClickListener itemClickListener;
    public BoarViewHolder(@NonNull View itemView) {
        super(itemView);
        cvboarsitem=itemView.findViewById(R.id.cvboarsitem);
        boarid=itemView.findViewById(R.id.boar_id);
        breed=itemView.findViewById(R.id.breed);
        color=itemView.findViewById(R.id.color);
        age=itemView.findViewById(R.id.age);
        weight=itemView.findViewById(R.id.weight);
        service=itemView.findViewById(R.id.service);
        sow=itemView.findViewById(R.id.sow);
        date=itemView.findViewById(R.id.date);
        days=itemView.findViewById(R.id.days);

    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }


    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
