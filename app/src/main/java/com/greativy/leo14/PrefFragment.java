package com.greativy.leo14;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.preference.PreferenceFragmentCompat;;

/**
 * Created by leokh on 6/12/2016.
 */

public class PrefFragment extends PreferenceFragmentCompat {

    public static final String PREFERENCES_NAME = "myPreference"; //偏好設定名稱
        @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            this.getPreferenceManager().setSharedPreferencesName(PREFERENCES_NAME);
            addPreferencesFromResource(R.xml.preference);
        }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

    }

}
