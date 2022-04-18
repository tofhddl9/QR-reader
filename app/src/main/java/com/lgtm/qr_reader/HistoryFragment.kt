package com.lgtm.qr_reader

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.lgtm.qr_reader.databinding.FragmentHistoryBinding
import com.lgtm.qr_reader.delegate.viewBinding

class HistoryFragment : Fragment(R.layout.fragment_history) {

    private val binding by viewBinding(FragmentHistoryBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}