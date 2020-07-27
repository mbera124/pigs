package com.example.njoro.myproject.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.njoro.myproject.R;
import com.example.njoro.myproject.model.Sow;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;

public class AddSow extends AppCompatActivity {
    EditText etbreed, etcolor, etweight, etservice, etmatimgboar, etmatingdate, etremainingdays, etage;
    Button btnsave;

    private FirebaseAuth auth;
    String userName;

    FirebaseDatabase database;
    DatabaseReference sows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sow);
        etbreed = findViewById(R.id.breed);
        etcolor = findViewById(R.id.color);
        etage = findViewById(R.id.age);
        etweight = findViewById(R.id.weight);
        etservice = findViewById(R.id.service);
        etmatimgboar = findViewById(R.id.matingBoar);
        etmatingdate = findViewById(R.id.matingdate);
        etremainingdays = findViewById(R.id.daysremaining);
        btnsave = findViewById(R.id.save);
        auth = FirebaseAuth.getInstance();


        String key = auth.getCurrentUser().getUid();
        database = FirebaseDatabase.getInstance();
        sows = database.getReference("Sow").child(key);
        userName = auth.getCurrentUser().getDisplayName();


        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showAlertDialog();
            }
        });


    }

    @Exclude
    private void showAlertDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddSow.this);
        alertDialog.setTitle("One more step!");
        alertDialog.setMessage("Enter Pig ID");
        final EditText edtpiggid = new EditText(AddSow.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        edtpiggid.setLayoutParams(layoutParams);
        alertDialog.setView(edtpiggid);
//        alertDialog.setIcon(R.drawable.cart);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                sows = sows.child(String.valueOf(System.currentTimeMillis()));

                String sowKey = sows.push().getKey();
                Sow sow = new Sow(
                        userName,
                        etbreed.getText().toString(),
                        etcolor.getText().toString(),
                        etage.getText().toString(),
                        etweight.getText().toString(),
                        etmatimgboar.getText().toString(),
                        etmatingdate.getText().toString(),
                        etremainingdays.getText().toString(),
                        edtpiggid.getText().toString()

                );


                sows.child("PigId").setValue(edtpiggid.getText().toString());
                sows.child("Breed").setValue(etbreed.getText().toString());
                sows.child("Color").setValue(etcolor.getText().toString());
                sows.child("Age").setValue(etage.getText().toString());
                sows.child("Weight").setValue(etweight.getText().toString());
                sows.child("ServiceRecord").setValue(etservice.getText().toString());
                sows.child("MatingBoar").setValue(etmatimgboar.getText().toString());
                sows.child("MatingDate").setValue(etmatingdate.getText().toString());
                sows.child("RemainingDays").setValue(etremainingdays.getText().toString());

                sows.push().setValue(sow);


                Toast.makeText(AddSow.this, "Your Sow Has Been Added!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        alertDialog.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}

