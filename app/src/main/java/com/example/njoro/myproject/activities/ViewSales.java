package com.example.njoro.myproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.adapter.ExpensesAdapter;
import com.example.njoro.myproject.adapter.SalesAdapter;
import com.example.njoro.myproject.model.Expenses;
import com.example.njoro.myproject.model.Sales;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewSales extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private FirebaseAuth auth;
    ProgressDialog mProgressDialog;
    RecyclerView recycler_sales;
    RecyclerView.LayoutManager layoutManager;
    private SalesAdapter salesAdapter;
    private List<Sales> salesList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sales);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Sale").child(auth.getUid());

        salesAdapter= new SalesAdapter(this, salesList);

        //load menu
        recycler_sales = findViewById(R.id.recycler_sales);
        salesAdapter = new SalesAdapter(this, salesList);
        recycler_sales.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 1);
        recycler_sales.setLayoutManager(layoutManager);
        recycler_sales.setAdapter(salesAdapter);
        loadSales();

    }

    private void loadSales(){
        showProgressDialog();
        if(salesList.size() >0){
            salesList.clear();
        }
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                        if (postSnapShot != null) {
                            Sales sales = new Sales();
                            sales.setDate(postSnapShot.child("Sale Date").getValue().toString());
                            sales.setType(postSnapShot.child("Sale Type").getValue().toString());
                            sales.setAmount(postSnapShot.child("Amount Received").getValue().toString());
                            salesList.add(sales);
                        }


                    }
                    //Toast.makeText(Home.this, "" + categoryList.size(), Toast.LENGTH_SHORT).show();

                    salesAdapter.notifyDataSetChanged();
                }
                hideProgressDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                hideProgressDialog();
            }
        });
    }
    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("Loading Sales...");
            mProgressDialog.setIndeterminate(true);
        }
        mProgressDialog.show();
    }


    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
