package com.lgtm.qr_reader.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.lgtm.qr_reader.data.QrHistoryRepository
import com.lgtm.qr_reader.model.QrData
import kotlinx.coroutines.launch

class ScanResultViewModel(
    private val historyRepository: QrHistoryRepository
): ViewModel() {

    private val _lastQrData: MutableLiveData<QrData> = MutableLiveData()
    val lastQrData: LiveData<QrData>
        get() = _lastQrData

    fun insert(qrData: QrData) = viewModelScope.launch {
        if (_lastQrData.value == qrData) return@launch

        _lastQrData.value = qrData
        historyRepository.insert(qrData)
    }
}

class ScanResultViewModelFactory(
    private val historyRepository: QrHistoryRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScanResultViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return ScanResultViewModel(historyRepository) as T
        }
        throw IllegalArgumentException("Unknown VieModel Class")
    }

}
