package com.lgtm.qr_reader

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lgtm.qr_reader.databinding.FragmentMainBinding
import com.lgtm.qr_reader.delegate.viewBinding

import com.journeyapps.barcodescanner.BarcodeCallback
import com.lgtm.qr_reader.model.QrData

class MainFragment: Fragment(R.layout.fragment_main) {

    private val binding by viewBinding(FragmentMainBinding::bind)

    private val callback: BarcodeCallback = BarcodeCallback { result ->
        if (result.text != null) {
            val qrData = QrData(
                text = result.text,
                timeStamp = result.timestamp
            )

            val action = MainFragmentDirections.actionMainFragmentToScanResultFragment(qrData)
            view?.findNavController()?.navigate(action)
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