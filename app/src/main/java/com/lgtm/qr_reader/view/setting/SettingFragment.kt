package com.lgtm.qr_reader.view.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.lgtm.qr_reader.R

class SettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_setting, rootKey)
    }

}