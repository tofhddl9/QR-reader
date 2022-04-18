package com.lgtm.qr_reader

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.lgtm.qr_reader.databinding.FragmentScanResultBinding
import com.lgtm.qr_reader.delegate.viewBinding
import com.lgtm.qr_reader.model.QrData
import com.lgtm.qr_reader.utils.isValidWebUrl
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.qr_reader.utils.DateUtil
import com.lgtm.qr_reader.viewmodels.ScanResultViewModel
import com.lgtm.qr_reader.viewmodels.ScanResultViewModelFactory

class ScanResultFragment : Fragment(R.layout.fragment_scan_result) {

    private val binding by viewBinding(FragmentScanResultBinding::bind)

    private val viewModel: ScanResultViewModel by viewModels {
        ScanResultViewModelFactory(((requireActivity().application) as QrReaderApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: ScanResultFragmentArgs by navArgs()
        initViews(args.qrData)
        observeViewModel()

        viewModel.insert(args.qrData)
    }
    private fun initViews(qrData: QrData) {
        binding.backButton.setOnClickListener {
            popFragment()
        }

        binding.actionButton.setOnClickListener {
            callToAction(qrData.text)
        }
    }

    private fun popFragment() {
        findNavController().popBackStack()
    }

    private fun callToAction(uriString: String?) {
        Intent().apply {
            action = "android.intent.action.VIEW"
            data = Uri.parse(uriString)
        }.also {
            startActivity(it)
        }
    }

    private fun observeViewModel() {
        viewModel.lastQrData.observe(viewLifecycleOwner) { qrData ->
            updateScanResultViews(qrData)
        }
    }

    private fun updateScanResultViews(qrData: QrData) {
        binding.scanResultTextView.text = qrData.text

        binding.scanResultTimeStamp.text = DateUtil.getDateTime(qrData.timeStamp ?: 0)
        binding.scanResultTimeStamp.isVisible = qrData.timeStamp != null

        binding.actionButton.isVisible = qrData.text.isValidWebUrl()
    }

}