package com.simple_sqllitesample;

import static com.simple_sqllitesample.R.*;

import static java.lang.Integer.*;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.Objects;

public  class Custom_Adapter extends Adapter<Custom_Adapter.MyViewHolder>{
    private Context context;
    private ArrayList book_id, book_title, book_author, book_pages;
    private MyViewHolder holder;
    private int position;
    private TextView tv_id, tv_booktitle, tv_bookauthor, tv_bookpages;

    public Custom_Adapter(Context context, ArrayList book_id, ArrayList book_title, ArrayList book_author, ArrayList book_pages) {
        this.context = context;
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_pages = book_pages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
       View view = layoutInflater.inflate(layout.myrow_data, parent, false);
       return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Custom_Adapter.MyViewHolder holder,
                                 int position) {
        this.holder = holder;
        this.position = position;
        Objects.requireNonNull(holder).tv_id.setText(String.valueOf(book_id.get(position)));
        Objects.requireNonNull(holder).tv_booktitle.setText(String.valueOf(book_title.get(position)));
        Objects.requireNonNull(holder).tv_bookauthor.setText(String.valueOf(book_author.get(position)));
        Objects.requireNonNull(holder).tv_bookpages.setText(String.valueOf(book_pages.get(position)));
        holder.row_index_key.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,Update_Data.class);
                intent.putExtra("id",String.valueOf(book_id.get(position)));
                intent.putExtra("title",String.valueOf(book_title.get(position)));
                intent.putExtra("author",String.valueOf(book_author.get(position)));
                intent.putExtra("pages",String.valueOf(book_pages.get(position)));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return book_id.size();
    }

    public class MyViewHolder extends ViewHolder{
        public TextView tv_id;
        private TextView tv_booktitle;
        public TextView tv_bookauthor;
        public TextView tv_bookpages;
        public LinearLayout row_index_key;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_id = Objects.requireNonNull(itemView).findViewById(id.tv_id);
            tv_booktitle = Objects.requireNonNull(itemView).findViewById(id.tv_booktitle);
            tv_bookauthor = Objects.requireNonNull(itemView).findViewById(id.tv_bookauthor);
            tv_bookpages = Objects.requireNonNull(itemView).findViewById(id.tv_bookpages);
            row_index_key = Objects.requireNonNull(itemView).findViewById(id.row_index_key);

        }


    }


}
