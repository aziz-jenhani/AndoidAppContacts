package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Ajout extends AppCompatActivity {
    private Button btnQuit;
    private Button btnValidate;
    private EditText inpNom, inpNum,inPrenom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout);

        btnQuit = this.findViewById(R.id.btnQuit);
        btnValidate = this.findViewById(R.id.btnValid);
        inpNom = this.findViewById(R.id.idNom);
        inpNum = this.findViewById(R.id.idNumero);
        inPrenom = this.findViewById(R.id.idPrenom);

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom = inpNom.getText().toString();
                String prenom = inPrenom.getText().toString();
                String numero = inpNum.getText().toString();
                Acceuil.addNewContact(new ContactData(
                        nom,
                        prenom,
                        numero
                ));
                displayMessage("Contact added Successfully");


                // Email is valid
                    //Intent i =new Intent(MainActivity.this,Acceuil.class);
                    //i.putExtra("User",email);
                    //startActivity(i);
                    //displayMessage("Email is valid");

            }
        });

    }
    private void displayMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}