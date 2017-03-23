package ru.rassvetmedia.totalconrolbeta.db;

import android.provider.BaseColumns;

/**
 * Created by Vasilij on 20.03.2017.
 */

public class DataBaseContract {
    public DataBaseContract() {

    }

    public static abstract class AccountsEntry implements BaseColumns {
        public static final String TABLE_NAME = "accounts";
        public static final String KEY_LOGIN = "login";
        public static final String KEY_PASSWORD = "password";
        public static final String KEY_NICKNAME = "nickname";
        public static final String KEY_FIRST_NAME = "first_name";
        public static final String KEY_LAST_NAME = "last_name";
        public static final String KEY_SOCIAL_ID = "social_id";
        public static final String KEY_GAME_ID = "game_id";
        public static final String KEY_COOKIES = "cookies";
        public static final String KEY_TIME_UPDATE = "time_update";
        public static final String KEY_ACCOUNT_STATUS = "status";

        public static final String TEXT_TYPE = " TEXT";
        public static final String INTEGER_TYPE = " INTEGER";
        public static final String COMMA_SEP = ",";

        public static final String SQL_CREATE_ENTRY =
                "CREATE TABLE "
                        + AccountsEntry.TABLE_NAME +
                        " ("
                        + AccountsEntry._ID + AccountsEntry.INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + AccountsEntry.COMMA_SEP
                        + AccountsEntry.KEY_LOGIN + AccountsEntry.TEXT_TYPE + AccountsEntry.COMMA_SEP
                        + AccountsEntry.KEY_PASSWORD + AccountsEntry.TEXT_TYPE + AccountsEntry.COMMA_SEP
                        + AccountsEntry.KEY_NICKNAME + AccountsEntry.TEXT_TYPE + AccountsEntry.COMMA_SEP
                        + AccountsEntry.KEY_FIRST_NAME + AccountsEntry.TEXT_TYPE + AccountsEntry.COMMA_SEP
                        + AccountsEntry.KEY_LAST_NAME + AccountsEntry.TEXT_TYPE + AccountsEntry.COMMA_SEP
                        + AccountsEntry.KEY_SOCIAL_ID + AccountsEntry.INTEGER_TYPE + AccountsEntry.COMMA_SEP
                        + AccountsEntry.KEY_COOKIES + AccountsEntry.TEXT_TYPE + AccountsEntry.COMMA_SEP
                        + AccountsEntry.KEY_TIME_UPDATE + AccountsEntry.INTEGER_TYPE + AccountsEntry.COMMA_SEP
                        + AccountsEntry.KEY_ACCOUNT_STATUS + AccountsEntry.INTEGER_TYPE +
                        ")";

        public static final String SQL_DELETE_ENTRY =
                "DROP TABLE IF EXISTS " + AccountsEntry.TABLE_NAME;


    }
}
