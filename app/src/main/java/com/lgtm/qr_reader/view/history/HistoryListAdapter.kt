package com.lgtm.qr_reader.view.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lgtm.qr_reader.databinding.ItemQrHistoryBinding
import com.lgtm.qr_reader.model.QrData
import com.lgtm.qr_reader.utils.DateUtil

class QrHistoryDiffCallback : DiffUtil.ItemCallback<QrData>() {
    override fun areItemsTheSame(oldItem: QrData, newItem: QrData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: QrData, newItem: QrData): Boolean {
        return oldItem == newItem
    }

}

class HistoryListAdapter: ListAdapter<QrData, HistoryListAdapter.VH>(QrHistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemQrHistoryBinding.inflate(layoutInflater, parent, false)

        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }


    class VH(
        private val binding: ItemQrHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: QrData) {
            binding.icon.setImageResource(item.type.iconRes)

            binding.qrCodeText.text = item.text
            binding.qrCodeText.isVisible = item.text != null

            binding.qrCodeTimestamp.isVisible = item.timeStamp != null
            binding.qrCodeTimestamp.text = DateUtil.getDateTime(item.timeStamp ?: 0)

            binding.root.setOnClickListener {
                val navAction = HistoryFragmentDirections.actionHistoryFragmentToScanResultFragment(item)
                binding.root.findNavController().navigate(navAction)
            }
        }
    }
}