package ui.zhuo.com.cursoradapterlib;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiazhuo.ren on 2017/6/7.
 */

public class TestContentProvider extends ContentProvider {
    public static final Uri CONTENT_URI = Uri
            .parse("content://ui.zhuo.com.cursoradapterlib");
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String LENGTH = "length";
    private static SQLiteDatabase database;
    private static final int DATABASE_VERSION = 2;
    private static final List<River> rivers = new ArrayList<River>();
    static {
        River river = new River("长江", 6380);
        rivers.add(river);
        river = new River("黄河", 5464);
        rivers.add(river);
    }

    private ContentResolver contentResolver;

    @Override
    public boolean onCreate() {
        database = new RiverDatabaseHelper(getContext(), "rivers", null,
                DATABASE_VERSION,rivers).getReadableDatabase();
        contentResolver = getContext().getContentResolver();
        return database != null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
     Cursor cursor=   database.query("rivers", projection, selection, selectionArgs,
                null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri u = null;
        long d = database.insert("rivers", null, values);
        u = ContentUris.withAppendedId(uri,d);
        contentResolver .notifyChange(u,null);
        return u;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        int d = database.update("rivers", contentValues, s,strings);
        Uri  u = ContentUris.withAppendedId(uri,d);
        contentResolver .notifyChange(u,null);
        return d;
    }

}
