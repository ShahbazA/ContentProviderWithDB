package com.contentprovider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    SqliteHelper sqliteHelper;
    MyProvider mMyProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println();

        sqliteHelper = new SqliteHelper(this);

        sqliteHelper.InsertInDB("col1","col2");

        mMyProvider = new MyProvider();
        String url = mMyProvider.CONTENT_URI_TABLE1.toString();

//        Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder
        ContentValues mContentValues = new ContentValues();
        mContentValues.put("column1","column1_provider_test");
        mContentValues.put("column2","column2_provider_test");
        Uri mUri = getContentResolver().insert(Uri.parse(url),mContentValues);

        Cursor c = getContentResolver().query(mUri,null,null,null,null);

        if (c.moveToFirst()) {
            do{
                Toast.makeText(this, c.getString(0) + ", " +  c.getString(1) + ", " + c.getString(2), Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }

        Cursor cursor = sqliteHelper.getAllInUpdateTable();
        if(cursor.moveToFirst()){
            do {

                String str = cursor.getString(1);
                System.out.println();


                Cursor mCursor = getContentResolver().query(Uri.parse(cursor.getString(1)),null,null,null,null);
                if(mCursor.moveToFirst()){
                    do {
                        Log.e("primary_keys_in_update",mCursor.getString(0));
                    }while (mCursor.moveToNext());
                }
            }while (cursor.moveToNext());
        }

    }
}
