package com.greativy.leo14;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;;

/**
 * Created by leokh on 6/12/2016.
 */

public class PrefFragment extends PreferenceFragmentCompat {
        @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference);
        }

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

    }

}
