package ru.rassvetmedia.totalconrolbeta.db.dao;

import android.content.Context;

/**
 * Created by Vasilij on 27.03.2017.
 */

public interface OperateAccountsDao {
    public long addAccountFromDialog(Context context, String nickname, String login, String password);
}
