package com.santiagocoronel.mozpertest.preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.santiagocoronel.mozpertest.BuildConfig;

public class PreferencesManager {

    private static String TAG = PreferencesManager.class.getSimpleName();

    private static String KEY = BuildConfig.APPLICATION_ID;
    private static volatile PreferencesManager instance = null;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Context context;

    //region keys
    public static final String KEY_LOGGED = "LOGGED";

    //endregion

    private PreferencesManager(Context context) {
        this.context = context;
    }

    public static void initialize(Context context) {
        if (instance != null) {
            Log.e(TAG, "PreferenceManager is already initialized");
            return;
        }
        instance = new PreferencesManager(context);
        instance.init();
    }

    public static PreferencesManager getInstance() {
        if (instance == null) {
            try {
                throw new Exception("PreferenceManager is not initialized");
            } catch (Exception e) {
                e.printStackTrace();
                System.exit(0);
            }
        }
        return instance;
    }

    public SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    public void saveKey(String key, String vale) {
        if (!key.isEmpty()) {
            initEditMode();
            editor.putString(key, vale);
            softClose();
        }
    }

    public String getKey(String key) {
        return prefs.getString(key, "");
    }


    private void init() {
        prefs = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    private void close() {
        editor.commit();
    }

    private void softClose() {
        editor.apply();
    }

    private void initEditMode() {
        editor = prefs.edit();
    }

    public void clean() {
        getSharedPreferences().edit().clear().apply();
    }
}