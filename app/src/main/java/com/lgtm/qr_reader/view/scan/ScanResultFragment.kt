package com.lgtm.qr_reader.view.scan

import android.content.ActivityNotFoundException
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.lgtm.qr_reader.databinding.FragmentScanResultBinding
import com.lgtm.qr_reader.delegate.viewBinding
import com.lgtm.qr_reader.model.QrData
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lgtm.qr_reader.QrReaderApplication
import com.lgtm.qr_reader.R
import com.lgtm.qr_reader.utils.DateUtil
import com.lgtm.qr_reader.viewmodels.ScanResultViewModel
import com.lgtm.qr_reader.viewmodels.ScanResultViewModelFactory

// TODO : 별도의 Activity로 만드는게 좋을 것 같다
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

        binding.actionButton.text = qrData.type.description
        binding.actionButton.setOnClickListener {
            callToAction(qrData)
        }
    }

    private fun popFragment() {
        findNavController().popBackStack()
    }

    // TODO : need to refactor
    private fun callToAction(qrData: QrData) {
        val intent = Intent().apply {
            action = qrData.type.action
            data = Uri.parse(qrData.text)
        }
        try {
            startActivity(intent)
        } catch(e : ActivityNotFoundException) {
            Toast.makeText(requireContext(), "지원하지 않는 형식이거나 텍스트입니다.", Toast.LENGTH_SHORT).show()
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
    }

}