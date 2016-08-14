package com.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObserver;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Handler;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by Shahbaz on 8/14/2016.
 */
public class MyContentObserver extends ContentObserver {

    Handler mHandler;
    Context context;

    public MyContentObserver(Handler handler, Context context) {
        super(handler);
        mHandler = handler;
        this.context = context;
    }

    @Override
    public void onChange(boolean selfChange, Uri uri) {
        if(mHandler != null) {
            super.onChange(selfChange, uri);
            ContentValues values = new ContentValues();
            values.put(SqliteHelper.TABLE_UPDATE_COL_URI,uri.toString());
            ContentProvider cp = context.getContentResolver().acquireContentProviderClient(uri).getLocalContentProvider();;
            MyProvider myCp = (MyProvider)cp;

            myCp.Insert_in_update_table(values);
        }
    }
}
