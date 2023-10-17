package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnCancel;
    private Button btnValidate;
    private EditText inpEmail, inpPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //MainActivity.this;
        setContentView(R.layout.activity_main);

        btnCancel = this.findViewById(R.id.btnCancel_main);
        btnValidate = this.findViewById(R.id.btnValidate_main);
        inpEmail = this.findViewById(R.id.idEmailMain);
        inpPass = this.findViewById(R.id.idPasswordMain);

        // Events
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inpEmail.getText().toString();
                String pass = inpPass.getText().toString();

                if (email.equalsIgnoreCase("") && pass.equals("")) {
                    // Email is valid
                    Intent i =new Intent(MainActivity.this,Acceuil.class);
                    i.putExtra("User",email);
                    startActivity(i);
                    displayMessage("Email is valid");
                } else {
                    // Email is not valid
                    displayError("Invalid email");
                }
            }
        });
    }

    // Helper method to display a toast message
    private void displayMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    // Helper method to display an error toast message
    private void displayError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
    }
}
