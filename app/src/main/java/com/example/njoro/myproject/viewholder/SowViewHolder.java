package com.example.njoro.myproject.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njoro.myproject.ItemClickListener;
import com.example.njoro.myproject.R;

public class SowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView sowid,breed,color,age,weight,service,boar,date,days;
    public CardView cvsowitem;
    private ItemClickListener itemClickListener;

    public SowViewHolder(@NonNull View itemView) {
        super(itemView);
        cvsowitem=itemView.findViewById(R.id.cvsowitem);
        sowid=itemView.findViewById(R.id.sow_id);
        breed=itemView.findViewById(R.id.breed);
        color=itemView.findViewById(R.id.color);
        age=itemView.findViewById(R.id.age);
        weight=itemView.findViewById(R.id.weight);
        service=itemView.findViewById(R.id.service);
        boar=itemView.findViewById(R.id.boar);
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
