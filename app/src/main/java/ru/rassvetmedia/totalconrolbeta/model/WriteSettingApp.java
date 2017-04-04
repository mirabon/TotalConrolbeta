package ru.rassvetmedia.totalconrolbeta.model;

import android.content.Context;

import ru.rassvetmedia.totalconrolbeta.pojo.Constans;

/**
 * Created by Vasilij on 04.04.2017.
 */

public class WriteSettingApp {
    private SharedPreferense data;

    public WriteSettingApp(Context context) {
        data = new SharedPreferense(Constans.SETTINGS_APP_NAME);
        data.init(context);
    }

    public void setData(String value1, String value2){
        data.addProperty("social_type", value1);
        data.addProperty("game_type", value2);
    }
}
