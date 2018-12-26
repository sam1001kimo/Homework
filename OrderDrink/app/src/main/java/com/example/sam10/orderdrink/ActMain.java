package com.example.sam10.orderdrink;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ActMain extends AppCompatActivity {

    private LinearLayout parentLinearLayout;
    EditText edittext_var;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actmain);
        parentLinearLayout = (LinearLayout) findViewById(R.id.parent_linear_layout);


    }

    public void onAddField(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View rowView = inflater.inflate(R.layout.field, null);
        // Add the new row before the add field button.
        parentLinearLayout.addView(rowView, parentLinearLayout.getChildCount() - 1);

    }

    public void onDelete(View v) {


        parentLinearLayout.removeView((View) v.getParent());

    }

    public void onCheckout(View v) {

    }

    public void onSelect(View v) { // on click event for a SELECT button

        edittext_var = (EditText) ((View) v.getParent()).findViewById(R.id.number_edit_text);
        Toast.makeText(this, edittext_var.getText().toString(), Toast.LENGTH_SHORT).show();
        //System.out.println("The text is = "+ edittext_var.getText());
    }


}