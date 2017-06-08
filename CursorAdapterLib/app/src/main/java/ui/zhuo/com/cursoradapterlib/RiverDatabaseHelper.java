package ui.zhuo.com.cursoradapterlib;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.List;

/**
 * Created by jiazhuo.ren on 2017/6/7.
 */

public class RiverDatabaseHelper extends SQLiteOpenHelper {
    List<River> rivers;
    public RiverDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version,List<River> rivers) {
        super(context, name, factory, version);
        this.rivers=rivers;
    }

    public RiverDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists rivers("
                + " _id integer primary key autoincrement," + " name text,"
                + "length integer" + ");");
        SQLiteStatement statement = sqLiteDatabase
                .compileStatement("insert into rivers(name,length) values(?,?)");

        for (River r : rivers) {
            statement.bindString(1, r.name);
            statement.executeInsert();
        }
        statement.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists rivers");
        onCreate(sqLiteDatabase);
    }
}
