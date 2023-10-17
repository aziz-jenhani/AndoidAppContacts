package com.example.test;
import android.app.AlertDialog;
import android.content.DialogInterface;
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;
import java.util.zip.Inflater;

public class MyconstactAdapter extends BaseAdapter {
    Context con;
    ArrayList<ContactData> d;
    ArrayList<ContactData> filteredList; // Add a filtered list

    public MyconstactAdapter(Context con, ArrayList<ContactData> d) {
        this.con = con;
        this.d = d;
        this.filteredList = new ArrayList<>(d); // Initialize filtered list with all contacts

    }

    @Override
    public int getCount() {
        return filteredList.size(); // Use the filtered list size

    }

    @Override
    public Object getItem(int i) {
        return filteredList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater lf =  LayoutInflater.from(con);
        View l = lf.inflate(R.layout.view_contact, null);
        TextView fname  =l.findViewById(R.id.name);
        TextView fprenom  =l.findViewById(R.id.lastName);
        TextView fnum  =l.findViewById(R.id.number);
        ImageButton editbtn  =l.findViewById(R.id.idedit);
        ImageButton deletebtn  =l.findViewById(R.id.idDelete);
        ImageButton callbtn  =l.findViewById(R.id.idcall);

        fname.setText(this.d.get(i).getName());
        fprenom.setText(this.d.get(i).getLastName());
        fnum.setText(this.d.get(i).getPhoneNumber());


        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                d.remove(d.get(i));
                notifyDataSetChanged();
            }
        });

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                con.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ d.get(i).getPhoneNumber().toString())));
            }
        });

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditDialog(i);
            }
        });

        return l;
    }

    public void filter(String query) {
        query = query.toLowerCase(Locale.getDefault());
        filteredList.clear();

        if (query.isEmpty()) {
            // If the query is empty, show all contacts
            filteredList.addAll(d);
        } else {
            // Filter the contacts based on the query
            for (ContactData contact : d) {
                if (contact.getName().toLowerCase(Locale.getDefault()).contains(query) ||
                        contact.getLastName().toLowerCase(Locale.getDefault()).contains(query) ||
                        contact.getPhoneNumber().toLowerCase(Locale.getDefault()).contains(query)) {
                    filteredList.add(contact);
                }
            }
        }

        notifyDataSetChanged();


    }

    private void showEditDialog(final int position) {
        LayoutInflater inflater = LayoutInflater.from(con);
        View editView = inflater.inflate(R.layout.activity_edit_contact, null);

        final EditText editName = editView.findViewById(R.id.editName);
        final EditText editLastName = editView.findViewById(R.id.editLastName);
        final EditText editPhoneNumber = editView.findViewById(R.id.editPhoneNumber);
        final Button saveButton = editView.findViewById(R.id.saveButton);

        editName.setText(d.get(position).getName());
        editLastName.setText(d.get(position).getLastName());
        editPhoneNumber.setText(d.get(position).getPhoneNumber());

        final AlertDialog dialog = new AlertDialog.Builder(con)
                .setTitle("Edit Contact")
                .setView(editView)
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Update the contact data with the edited values
                        d.get(position).setName(editName.getText().toString());
                        d.get(position).setLastName(editLastName.getText().toString());
                        d.get(position).setPhoneNumber(editPhoneNumber.getText().toString());
                        notifyDataSetChanged();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

        dialog.show();
    }
}

