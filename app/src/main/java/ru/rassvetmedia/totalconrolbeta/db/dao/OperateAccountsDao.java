package ru.rassvetmedia.totalconrolbeta.db.dao;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by Vasilij on 27.03.2017.
 */

public interface OperateAccountsDao {
    long addAccountFromDialog(Context context, String nickname, String login, String password);
    Cursor getAllAccounts(Context context);
    long deleteAccountForId(Context context, long id);
    long addTestAccount(Context context);
}
