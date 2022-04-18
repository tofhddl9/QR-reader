package com.lgtm.qr_reader

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceFragmentCompat
import com.lgtm.qr_reader.databinding.FragmentMainBinding
import com.lgtm.qr_reader.databinding.FragmentSettingBinding
import com.lgtm.qr_reader.delegate.viewBinding

class SettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preference_setting, rootKey)

//        findPreference<SwitchPreferenceCompat>(getString(R.string.key_sp_keep_the_screen_on))?.setOnPreferenceChangeListener { _, newValue ->
//            if (newValue == true) {
//                Toast.makeText(activity, "화면을 켜진 상태로 유지합니다.", Toast.LENGTH_SHORT).show()
//            } else {
//                Toast.makeText(activity, "화면의 켜진 상태를 유지하지 않습니다.", Toast.LENGTH_SHORT).show()
//            }
//
//            true
//        }

    }

}