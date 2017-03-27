package ru.rassvetmedia.totalconrolbeta.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Vasilij on 27.03.2017.
 */

public class GetSQLiteOpenHelper {
    private static SQLiteOpenHelper sqLiteOpenHelper = null;

    public static SQLiteOpenHelper getHelperInstance(Context context) {
        if (sqLiteOpenHelper == null) {
            sqLiteOpenHelper = new DBHelper(context);
        }
        return sqLiteOpenHelper;
    }
}
