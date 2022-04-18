package com.lgtm.qr_reader

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.lgtm.qr_reader.databinding.FragmentMainBinding
import com.lgtm.qr_reader.delegate.viewBinding

import com.journeyapps.barcodescanner.BarcodeCallback

class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val callback: BarcodeCallback = BarcodeCallback { result ->
        if (result.text != null) {
            Log.d("Doran", "$result")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.qrcodeView.decodeContinuous(callback)
    }

    override fun onResume() {
        super.onResume()
        binding.qrcodeView.resume()
    }

    override fun onPause() {
        binding.qrcodeView.pause()
        super.onPause()
    }


}