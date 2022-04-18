package com.lgtm.qr_reader.data

import com.lgtm.qr_reader.model.QrData
import kotlinx.coroutines.flow.Flow

interface QrHistoryRepository {

    val allQrHistories: Flow<List<QrData>>

    suspend fun insert(qrData: QrData)

    suspend fun delete(qrData: QrData)

    suspend fun clear()
}