package com.simple_sqllitesample;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Update_Data extends AppCompatActivity {
    EditText tx_input1,tv_authorInput1,tv_pages1;
    Button btn_update,btn_delete;
    String id,title,author,pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data);
        tx_input1 = (EditText) findViewById(R.id.tx_input1);
        tv_authorInput1 = (EditText) findViewById(R.id.tv_authorInput1);
        tv_pages1 = (EditText) findViewById(R.id.tv_pages1);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button)findViewById(R.id.btn_delete);

        getIntentData();
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle("title");
        }



        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Dao dao = new Dao(Update_Data.this);
            dao.updateRecord(id,title,author,pages);

            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm_data();

            }
        });

    }
    private void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("author") && getIntent().hasExtra("pages")){
           //getting data from intent
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            author = getIntent().getStringExtra("author");
            pages = getIntent().getStringExtra("pages");

            //setting data to intent
            tx_input1.setText(title);
            tv_authorInput1.setText(author);
            tv_pages1.setText(pages);

        }
        else {
            Toast.makeText(this, "no data....", Toast.LENGTH_SHORT).show();
        }
    }
    void confirm_data(){
        AlertDialog.Builder builder = new AlertDialog.Builder(Update_Data.this);
        builder.setTitle("Delete "+ title + " ?");
        builder.setMessage("Are you sure you want to delete" + title + "?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Dao dao = new Dao(Update_Data.this);
                dao.deleterecordRow(id);
                finish();

            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Update_Data.this,"Ok no file has been deleted",Toast.LENGTH_LONG).show();
            }
        });
        builder.create().show();
    }
}