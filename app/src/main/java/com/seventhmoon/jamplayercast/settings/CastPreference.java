package com.seventhmoon.jamplayercast.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;

import com.seventhmoon.jamplayercast.R;
import com.seventhmoon.jamplayercast.utils.Utils;

public class CastPreference extends PreferenceActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.application_preference);
        getPreferenceScreen().getSharedPreferences().
                registerOnSharedPreferenceChangeListener(this);

        EditTextPreference versionPref = (EditTextPreference) findPreference("app_version");
        versionPref.setTitle(getString(R.string.version, Utils.getAppVersionName(this)));
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Handle volume and caption preferences.
    }
}
