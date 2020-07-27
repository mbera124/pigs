package com.example.njoro.myproject.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njoro.myproject.ItemClickListener;
import com.example.njoro.myproject.R;

public class PigsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView contact,price,pricetxt,contacttxt;
    public ImageView imgpig;
    public CardView cvpigsitem;
    private ItemClickListener itemClickListener;

    public PigsViewHolder(@NonNull View itemView) {
        super(itemView);
        cvpigsitem=itemView.findViewById(R.id.cvpigsitem);
        price=itemView.findViewById(R.id.price);
        pricetxt=itemView.findViewById(R.id.pricetxt);
        contacttxt=itemView.findViewById(R.id.contacttxt);
        contact=itemView.findViewById(R.id.contact);
        imgpig=itemView.findViewById(R.id.imgpig);
    }
    public void setItemClickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }


    @Override
    public void onClick(View v) {
        itemClickListener.onClick(v,getAdapterPosition(),false);
    }
}
