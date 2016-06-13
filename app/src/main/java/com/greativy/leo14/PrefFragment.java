package com.greativy.leo14;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by leokh on 6/12/2016.
 */

public class PrefFragment extends PreferenceFragment{
        @Override
    public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preference);
        }

}
