package com.simple_sqllitesample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

public class Dao extends SQLiteOpenHelper{

    private Context context;
  private static final String DATABASE_NAME = "BookLibrary.db";
  private static final int DATABSE_VERSION = 1;

  private static final String TABLE_NAME = "b_library";
  private static final String COLUMN_ID = "_id";
  private static final String COLUMN_TITLE = "book_title";
  private static final String COLUMN_AUTHOR = "book_author";
  private static final String COLUMN_PAGES = "book_pages";



  public Dao(@Nullable Context context) {
    super(context, DATABASE_NAME, null, DATABSE_VERSION);
    this.context = context;

  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String querydb =
            "CREATE TABLE " + TABLE_NAME +
                    " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_TITLE + " TEXT, " +
                    COLUMN_AUTHOR + " TEXT, " +
                    COLUMN_PAGES + " INTEGER);";
    db.execSQL(querydb);

  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);

  }
  public void addbookdetails(String title, String author, int pages){
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues cv = new ContentValues();
      cv.put(COLUMN_TITLE,title);
      cv.put(COLUMN_AUTHOR,author);
      cv.put(COLUMN_PAGES,pages);

      long result = db.insert(TABLE_NAME,null,cv);
      if(result == -1){
          Toast.makeText(context,"Failed for some reason..Try again",Toast.LENGTH_SHORT);

      }
      else{
          Toast.makeText(context,"Succesfully added",Toast.LENGTH_LONG).show();
      }

  }
   Cursor readallDates(){
      String querydata = "SELECT * FROM " + TABLE_NAME;
      SQLiteDatabase db = this.getReadableDatabase();

      Cursor cursor = null;
      if(db != null){
          cursor = db.rawQuery(querydata,null);
      }
      return cursor;

   }
   void updateRecord(String title, String author, String pages,String row_id){
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues cv = new ContentValues();
      cv.put(COLUMN_TITLE,"title");
      cv.put(COLUMN_AUTHOR,"author");
      cv.put(COLUMN_PAGES,"pages");
     long result = db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
      if (result == -1){
       Toast.makeText(context,"Failed to update for some reason..",Toast.LENGTH_LONG).show();
      }
      else{
          Toast.makeText(context,"Successfully updated",Toast.LENGTH_LONG).show();
      }
   }
   void deleterecordRow(String row_id){
      SQLiteDatabase db = this.getReadableDatabase();
       long result;
       result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
       if(result == -1){
         Toast.makeText(context,"Failed to delete..try again",Toast.LENGTH_LONG).show();
     }
     else{
         Toast.makeText(context,"Successfully deleted",Toast.LENGTH_LONG).show();
     }
   }
   void getalldataDeleted(){
      SQLiteDatabase db = this.getWritableDatabase();
      db.execSQL("DELETE FROM "+ TABLE_NAME);
   }


}