package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Acceuil extends AppCompatActivity {

    private TextView tvusername;
    private Button btnajout,btnaffiche;
    public static ArrayList<ContactData> contacts = new ArrayList<ContactData>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        tvusername=findViewById(R.id.tvUser_accueil);
        btnajout=findViewById(R.id.btn_ajout);
        btnaffiche=findViewById(R.id.btn_affiche);
        Intent x =this.getIntent();
        Bundle b=x.getExtras();
        String user =b.getString("User");
        tvusername.setText("Accueil de M."+user);

        btnajout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Acceuil.this,Ajout.class);
                startActivity(i);
            }
        });
        btnaffiche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(Acceuil.this, ShowContactsListView.class);
                startActivity(i);
            }
        });

    }

    public static void  addNewContact(ContactData contactData) {
        Acceuil.contacts.add(contactData);
    }

}