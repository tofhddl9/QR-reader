package com.lgtm.qr_reader

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lgtm.qr_reader.databinding.FragmentMainBinding
import com.lgtm.qr_reader.delegate.viewBinding

class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView.text = "main"
    }

}