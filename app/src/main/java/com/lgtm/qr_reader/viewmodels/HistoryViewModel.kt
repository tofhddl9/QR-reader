package com.lgtm.qr_reader.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.lgtm.qr_reader.data.QrHistoryRepository
import com.lgtm.qr_reader.model.QrData
import kotlinx.coroutines.launch

class HistoryViewModel(
    private val historyRepository: QrHistoryRepository
): ViewModel() {

    val allHistories: LiveData<List<QrData>> = historyRepository.allQrHistories.asLiveData()

    // TODO : implement
    fun delete(qrData: QrData) = viewModelScope.launch {
        historyRepository.delete(qrData)
    }

    fun clear() = viewModelScope.launch {
        historyRepository.clear()
    }
}

class HistoryViewModelFactory(
    private val historyRepository: QrHistoryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return HistoryViewModel(historyRepository) as T
        }
        throw IllegalArgumentException("Unknown VieModel Class")
    }

}
