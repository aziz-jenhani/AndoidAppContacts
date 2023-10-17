package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class ShowContactsListView extends AppCompatActivity {
    private MyconstactAdapter listAdapter;
    private Button btnCancel;
    private EditText searchEditText; // Add an EditText for search

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        btnCancel = this.findViewById(R.id.idCancelAff);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // Initialize the adapter and set it to the ListView
        listAdapter = new MyconstactAdapter(ShowContactsListView.this, Acceuil.contacts);
        ListView listView = (ListView) findViewById(R.id.idlistview);
        listView.setAdapter(listAdapter);

        // Add search functionality
        searchEditText = findViewById(R.id.searchEditText);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Update the filter as the user types
                listAdapter.filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

    }

}