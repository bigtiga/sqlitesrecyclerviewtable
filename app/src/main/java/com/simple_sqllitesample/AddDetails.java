package com.simple_sqllitesample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddDetails extends AppCompatActivity {
    EditText tx_input,tv_authorInput,tv_pages;
    Button btn_addAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_details);
        tx_input = (EditText) findViewById(R.id.tx_input);
        tv_authorInput = (EditText) findViewById(R.id.tv_authorInput);
        tv_pages = (EditText) findViewById(R.id.tv_pages);
        btn_addAll = (Button) findViewById(R.id.btn_addAll);

        btn_addAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dao dbhelper = new Dao(AddDetails.this);
                dbhelper.addbookdetails(tx_input.getText().toString().trim(),tv_authorInput.getText()
                        .toString().trim(),Integer.valueOf(tv_pages.getText().toString().trim()));



            }
        });


    }
}