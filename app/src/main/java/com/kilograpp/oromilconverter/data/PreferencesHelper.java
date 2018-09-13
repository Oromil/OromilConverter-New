package com.kilograpp.oromilconverter.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.kilograpp.oromilconverter.OromilConverterApplication;

import java.util.Set;

public class PreferencesHelper {

    private final String PREFERENCES_NAME = "com.kilograpp.oromilconverter";

    private final String CURRENCIES_SET_KEY = "selected_currencies";

    private final SharedPreferences preferences;

    public PreferencesHelper() {
        preferences = OromilConverterApplication.getInstance().getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public void saveSelectedCurrencies(Set<String> data) {
        preferences.edit().putStringSet(CURRENCIES_SET_KEY, data).apply();
    }

    public Set<String> getSelectedCurrencies() {
        return preferences.getStringSet(CURRENCIES_SET_KEY, null);
    }
}
