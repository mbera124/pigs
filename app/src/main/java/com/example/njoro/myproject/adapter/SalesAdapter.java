package com.example.njoro.myproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.model.Expenses;
import com.example.njoro.myproject.model.Sales;
import com.example.njoro.myproject.viewholder.ExpensesViewHolder;
import com.example.njoro.myproject.viewholder.SalesViewHolder;

import java.util.List;

public class SalesAdapter extends RecyclerView.Adapter<SalesViewHolder>{
    private List<Sales> salesList;
    private Context mContext;
    private String TAG = "SalesAdapter";
    public SalesAdapter(Context context, List<Sales> salesList) {
        this.mContext = context;
        this.salesList =salesList;

    }


    @NonNull
    @Override
    public SalesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.viewsales_layout, parent, false);
        return new SalesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SalesViewHolder holder, int position) {
        Sales sales =salesList.get(position);
        holder.date.setText(sales.getDate());
        holder.type.setText(sales.getType());
        holder.amount.setText(sales.getAmount());
        holder.cvsalesitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick: clicked on: " + salesList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return salesList==null?0:salesList.size();
    }
}
