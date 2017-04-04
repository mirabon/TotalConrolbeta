package ru.rassvetmedia.totalconrolbeta.model;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

import ru.rassvetmedia.totalconrolbeta.pojo.Constans;

/**
 * Created by Vasilij on 08.01.2017.
 */

public class SharedPreferense {
    public static String STORAGE_NAME = Constans.SETTINGS_APP_NAME;

    private static SharedPreferences settings = null;
    private static SharedPreferences.Editor editor = null;
    private static Context context = null;
    private Map<String,?> prefsMap;

    public SharedPreferense(String name) {
        this.STORAGE_NAME = name;
    }
    public SharedPreferense() {

    }
    public static void init( Context cntxt ){
        context = cntxt;
        init();
    }

    private static void init(){
        settings = context.getSharedPreferences(STORAGE_NAME, Context.MODE_PRIVATE);
        editor = settings.edit();
    }

    public static void addProperty( String name, String value ){
        if( settings == null ){
            init();
        }
        editor.putString( name, value );
        editor.apply();
    }

    public static String getProperty( String name ){
        if( settings == null ){
            init();
        }
        return settings.getString( name, null );
    }
    public static void clearAllData(){
        if( settings != null ){
            editor.clear();
            editor.apply();
        }
    }
    public static boolean getSettingsStatus(){
        if (settings != null){
            return true;
        } else {
            return false;
        }
    }

    public Map<String, ?> getAllPreferenses() {
        return this.prefsMap = settings.getAll();
    }
}
