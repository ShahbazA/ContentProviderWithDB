package com.contentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shahbaz on 8/14/2016.
 */
public class SqliteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "testing_db";


    public static final String TABLE_NAME = "table1";
    private static final String COLUMN1 = "column1";
    private static final String COLUMN2 = "column2";


    public static final String TABLE_UPDATE = "table_update";
    public static final String TABLE_UPDATE_COL_URI = "changed_uri";

    private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "( id INTEGER PRIMARY KEY AUTOINCREMENT, "+ COLUMN1 + " TEXT, "+ COLUMN2 +" TEXT)";
    private static final String CREATE_TABLE_UPDATE = "CREATE TABLE "+ TABLE_UPDATE + "( id INTEGER PRIMARY KEY AUTOINCREMENT, "+ TABLE_UPDATE_COL_URI +" TEXT)";

    SqliteHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        db.execSQL(CREATE_TABLE_UPDATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST "+TABLE_NAME);
        onCreate(db);
    }

    public void Insert_in_update_table(ContentValues values){
        SQLiteDatabase sql_db = getWritableDatabase();
        sql_db.insert(TABLE_UPDATE, null, values);
    }

    public Cursor getAllInUpdateTable(){
        DbHolder dbHolder = new DbHolder();
        ArrayList<DbHolder> list = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from " + TABLE_UPDATE;
        return db.rawQuery(query,null);
    }


    public void InsertInDB(String value_column1, String value_column2){

    }

    public Cursor getAllInDB(String id, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        DbHolder dbHolder = new DbHolder();
        ArrayList<DbHolder> list = new ArrayList();
        SQLiteDatabase db = getReadableDatabase();
        String query = "select * from " + TABLE_NAME + " where id = " + id;
        Cursor cursor = db.rawQuery(query,null);
        /*cursor.moveToFirst();

        do {
            dbHolder.setColumn1(cursor.getString(1));
            dbHolder.setColumn2(cursor.getString(2));
            list.add(dbHolder);
        }while (cursor.moveToNext());*/

//        cursor.moveToFirst();
        return cursor;
    }
}
