package com.kilograpp.oromilconverter.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.kilograpp.oromilconverter.OromilConverterApplication;

import java.util.Set;

public class PreferencesHelper {

    private final String PREFERENCES_NAME = "com.kilograpp.oromilconverter";

    private final String VALUTES_SET_KEY = "selected_valutes";

    private final SharedPreferences preferences;

    public PreferencesHelper() {
        preferences = OromilConverterApplication.getInstance().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void saveSelectedValutes(Set<String> data) {
        preferences.edit().putStringSet(VALUTES_SET_KEY, data).apply();
    }

    public Set<String> getSelectedValutes() {
        return preferences.getStringSet(VALUTES_SET_KEY, null);
    }
}
