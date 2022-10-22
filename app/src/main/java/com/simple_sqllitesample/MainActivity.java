package com.simple_sqllitesample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.CursorWindow;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    ImageView imageView2;
    public RecyclerView recyclerview;
    private FloatingActionButton btn_add;
    Dao dao;
    ArrayList<String> book_id, book_title, book_author,book_pages;
    Custom_Adapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        btn_add = (FloatingActionButton) findViewById(R.id.btn_add);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddDetails.class);
                startActivity(intent);
            }
        });
        dao = new Dao(MainActivity.this);

        book_id = new ArrayList<>();
        book_title = new ArrayList<>();
        book_author = new ArrayList<>();
        book_pages = new ArrayList<>();

        displayarrayData();
        customAdapter = new Custom_Adapter(MainActivity.this,book_id,book_title,book_author,book_pages);
        recyclerview.setAdapter(customAdapter);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        //customAdapter = new Custom_Adapter(MainActivity.this ,book_id,book_title,book_author,book_pages);
        //recyclerview.setAdapter(customAdapter);
        //recyclerview.setLayoutManager(new LinearLayoutManager(MainActivity.this));



    }
   void displayarrayData(){
        Cursor cursor = dao.readallDates();
        if(cursor.getCount()==0){
            imageView2.setVisibility(View.VISIBLE);
            textView1.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this,"No data shown..",Toast.LENGTH_LONG).show();
        }
        else{
            while (cursor.moveToNext()){
                book_id.add(cursor.getString(0));
                book_title.add(cursor.getString(1));
                book_author.add(cursor.getString(2));
                book_pages.add(cursor.getString(3));

            }
            imageView2.setVisibility(View.GONE);
            textView1.setVisibility(View.GONE);

        }

   }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            Toast.makeText(this,"Deleted",Toast.LENGTH_LONG).show();
            Dao dao = new Dao(this);
            dao.getalldataDeleted();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}