package com.example.njoro.myproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.adapter.SowAdapter;
import com.example.njoro.myproject.model.Sow;
import com.example.njoro.myproject.viewholder.SowViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewSow extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private FirebaseAuth auth;
    ProgressDialog mProgressDialog;
    RecyclerView recycler_sow;
    RecyclerView.LayoutManager layoutManager;
    private SowAdapter sowAdapter;
    private List<Sow> sowList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sow);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Sow").child(auth.getUid());

        sowAdapter= new SowAdapter(this, sowList);

        //load menu
        recycler_sow = findViewById(R.id.recycler_sow);
        sowAdapter = new SowAdapter(this, sowList);
        recycler_sow.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 1);
        recycler_sow.setLayoutManager(layoutManager);
        recycler_sow.setAdapter(sowAdapter);
        loadSow();

    }

    private void loadSow(){
        showProgressDialog();
        if(sowList.size() >0){
            sowList.clear();
        }
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                        if (postSnapShot != null) {
                            Sow sow = new Sow();
                            sow.setPigId(postSnapShot.child("PigId").getValue().toString());
                            sow.setBreed(postSnapShot.child("Breed").getValue().toString());
                            sow.setColor(postSnapShot.child("Color").getValue().toString());
                            sow.setAge(postSnapShot.child("Age").getValue().toString());
                            sow.setWeight(postSnapShot.child("Weight").getValue().toString());
                            sow.setServiceRecord(postSnapShot.child("ServiceRecord").getValue().toString());
                            sow.setMatingBoar(postSnapShot.child("MatingBoar").getValue().toString());
                            sow.setMatingDate(postSnapShot.child("MatingDate").getValue().toString());
                            sow.setRemainingDays(postSnapShot.child("RemainingDays").getValue().toString());
                            sowList.add(sow);
                        }


                    }
                    //Toast.makeText(Home.this, "" + categoryList.size(), Toast.LENGTH_SHORT).show();

                    sowAdapter.notifyDataSetChanged();
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
