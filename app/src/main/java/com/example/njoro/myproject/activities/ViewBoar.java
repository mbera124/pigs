package com.example.njoro.myproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.adapter.BoarAdapter;
import com.example.njoro.myproject.model.Boar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewBoar extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private FirebaseAuth auth;
    ProgressDialog mProgressDialog;
    RecyclerView recycler_boar;
    RecyclerView.LayoutManager layoutManager;
    private BoarAdapter boarAdapter;
    private List<Boar> boarList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_boar);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Boar").child(auth.getUid());

        boarAdapter= new BoarAdapter(this, boarList);

        //load menu
        recycler_boar = findViewById(R.id.recycler_boar);
        boarAdapter = new BoarAdapter(this, boarList);
        recycler_boar.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 1);
        recycler_boar.setLayoutManager(layoutManager);
        recycler_boar.setAdapter(boarAdapter);
        loadBoar();

    }

    private void loadBoar(){
        showProgressDialog();
        if(boarList.size() >0){
            boarList.clear();
        }
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot postSnapShot : dataSnapshot.getChildren()) {
                        if (postSnapShot != null) {
                            Boar boar = new Boar();
                            boar.setPigId(postSnapShot.child("PigId").getValue().toString());
                            boar.setBreed(postSnapShot.child("Breed").getValue().toString());
                            boar.setColor(postSnapShot.child("Color").getValue().toString());
                            boar.setAge(postSnapShot.child("Age").getValue().toString());
                            boar.setWeight(postSnapShot.child("Weight").getValue().toString());
                            boar.setServiceRecord(postSnapShot.child("ServiceRecord").getValue().toString());
                            boar.setMatingSow(postSnapShot.child("MatingSow").getValue().toString());
                            boar.setMatingDate(postSnapShot.child("MatingDate").getValue().toString());
                            boar.setRemainingDays(postSnapShot.child("RemainingDays").getValue().toString());
                            boarList.add(boar);
                        }


                    }
                    //Toast.makeText(Home.this, "" + categoryList.size(), Toast.LENGTH_SHORT).show();

                    boarAdapter.notifyDataSetChanged();
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
