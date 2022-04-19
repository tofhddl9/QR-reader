package com.lgtm.qr_reader.view.scan

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.lgtm.qr_reader.delegate.viewBinding

import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.lgtm.qr_reader.R
import com.lgtm.qr_reader.databinding.FragmentScanBinding
import com.lgtm.qr_reader.model.QrData
import com.lgtm.qr_reader.model.QrType

class ScanFragment: Fragment(R.layout.fragment_scan) {

    private val binding by viewBinding(FragmentScanBinding::bind)

    private val callback: BarcodeCallback = BarcodeCallback { result ->
        if (result.text != null) {
            val qrData = parseQrData(result)

            val navAction = ScanFragmentDirections.actionScanFragmentToScanResultFragment(qrData)
            view?.findNavController()?.navigate(navAction)
        }
    }

    // TODO : need to refactor
    private fun parseQrData(result: BarcodeResult): QrData {
        result.text ?: return QrData()

        var text = result.text.trim().lowercase()
        var type = QrType.TEXT

        // URL
        if (text.startsWith("http://") ||
            text.startsWith("https://")) {
            type = QrType.URL
        }
        if (text.startsWith("www.")) {
            text = "http://$text"
            type = QrType.URL
        }

        // TEL
        if (text.startsWith("tel:")) {
            type = QrType.TEL
        }

        // GEO
        if (text.startsWith("geo:")) {
            type = QrType.GEO
        }

        // EMAIL
        if (text.startsWith("mailto")) {
            type = QrType.EMAIL
        }

        return QrData(text = text, type = type, timeStamp = result.timestamp)
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