package com.example.njoro.myproject.viewholder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njoro.myproject.ItemClickListener;
import com.example.njoro.myproject.R;

public class SalesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView date,type,amount;
    public CardView cvsalesitem;
    private ItemClickListener itemClickListener;

    public SalesViewHolder(@NonNull View itemView) {
        super(itemView);
        cvsalesitem=itemView.findViewById(R.id.cvsalesitem);
        date=itemView.findViewById(R.id.date);
        type=itemView.findViewById(R.id.type);
        amount=itemView.findViewById(R.id.amount);
    }

    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }


    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
