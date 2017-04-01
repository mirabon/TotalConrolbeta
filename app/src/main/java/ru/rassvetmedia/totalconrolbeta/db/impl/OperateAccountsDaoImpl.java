package ru.rassvetmedia.totalconrolbeta.db.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ru.rassvetmedia.totalconrolbeta.db.DataBaseContract;
import ru.rassvetmedia.totalconrolbeta.db.GetSQLiteOpenHelper;
import ru.rassvetmedia.totalconrolbeta.db.dao.OperateAccountsDao;

import static java.sql.Types.NULL;

/**
 * Created by Vasilij on 27.03.2017.
 */

public class OperateAccountsDaoImpl implements OperateAccountsDao {
    @Override
    public long addAccountFromDialog(Context context, String nickname, String login, String password) {
        SQLiteOpenHelper sqLiteOpenHelper = GetSQLiteOpenHelper.getHelperInstance(context);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBaseContract.AccountsEntry.KEY_NICKNAME, nickname);
        contentValues.put(DataBaseContract.AccountsEntry.KEY_LOGIN, login);
        contentValues.put(DataBaseContract.AccountsEntry.KEY_PASSWORD, password);
        contentValues.put(DataBaseContract.AccountsEntry.KEY_FIRST_NAME, NULL);
        contentValues.put(DataBaseContract.AccountsEntry.KEY_LAST_NAME, NULL);
        contentValues.put(DataBaseContract.AccountsEntry.KEY_SOCIAL_ID, NULL);
        contentValues.put(DataBaseContract.AccountsEntry.KEY_COOKIES, NULL);
        contentValues.put(DataBaseContract.AccountsEntry.KEY_TIME_UPDATE, NULL);
        contentValues.put(DataBaseContract.AccountsEntry.KEY_ACCOUNT_STATUS, 0);
        long insert = db.insert(DataBaseContract.AccountsEntry.TABLE_NAME, null, contentValues);
        db.close();
        return insert;
    }

    @Override
    public Cursor getAllAccounts(Context context) {
        SQLiteOpenHelper sqLiteOpenHelper = GetSQLiteOpenHelper.getHelperInstance(context);
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        Cursor c = db.query(DataBaseContract.AccountsEntry.TABLE_NAME, null, null, null, null, null, null);
        db.close();
        return c;
    }
}
