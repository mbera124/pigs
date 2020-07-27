package com.example.njoro.myproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.adapter.PigsAdapter;
import com.example.njoro.myproject.model.Pigs;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BuyerActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ProgressDialog mProgressDialog;
    RecyclerView recycler_buyer;
    RecyclerView.LayoutManager layoutManager;
    private PigsAdapter pigsAdapter;
    private List<Pigs> pigsList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer);
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("SellPig");

        pigsAdapter= new PigsAdapter(this, pigsList);

        //load menu
        recycler_buyer = findViewById(R.id.recycler_buyer);
        pigsAdapter = new PigsAdapter(this, pigsList);
        recycler_buyer.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 1);
        recycler_buyer.setLayoutManager(layoutManager);
        recycler_buyer.setAdapter(pigsAdapter);
        loadPigs();


    }

    private void loadPigs() {
        showProgressDialog();
        if(pigsList.size() >0){
            pigsList.clear();
        }
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for (DataSnapshot mdataSnapshot : dataSnapshot.getChildren()){
                        Pigs pigs = mdataSnapshot.getValue(Pigs.class);
                        pigsList.add(pigs);
                    }


                }
                //Toast.makeText(Home.this, "" + categoryList.size(), Toast.LENGTH_SHORT).show();
                hideProgressDialog();
                pigsAdapter.notifyDataSetChanged();
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
            mProgressDialog.setMessage("Loading...");
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
