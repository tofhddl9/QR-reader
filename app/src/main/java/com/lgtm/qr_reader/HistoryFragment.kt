package com.lgtm.qr_reader

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.lgtm.qr_reader.databinding.FragmentHistoryBinding
import com.lgtm.qr_reader.delegate.viewBinding
import com.lgtm.qr_reader.viewmodels.HistoryViewModel
import com.lgtm.qr_reader.viewmodels.HistoryViewModelFactory

class HistoryFragment : Fragment(R.layout.fragment_history) {

    private val binding by viewBinding(FragmentHistoryBinding::bind)

    private val viewModel: HistoryViewModel by viewModels {
        HistoryViewModelFactory(((requireActivity().application) as QrReaderApplication).repository)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initRecyclerView()
    }

    private fun initToolbar() = with(binding.toolBar) {
        inflateMenu(R.menu.menu_history_tool_bar)
        setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_clear -> {
                    viewModel.clear()
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

    private fun initRecyclerView() {
        val adapter = HistoryListAdapter()
        binding.historyList.layoutManager = LinearLayoutManager(requireContext())
        binding.historyList.adapter = adapter

        viewModel.allHistories.observe(viewLifecycleOwner) {
            it?.let {
                adapter.submitList(it)
            }
        }
    }


}